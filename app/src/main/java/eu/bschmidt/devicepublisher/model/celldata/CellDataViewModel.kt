package eu.bschmidt.devicepublisher.model.celldata

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.SimPhonebookContract.ElementaryFiles.SUBSCRIPTION_ID
import android.telephony.SubscriptionInfo
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.mroczis.netmonster.core.db.model.NetworkType
import cz.mroczis.netmonster.core.factory.NetMonsterFactory
import cz.mroczis.netmonster.core.feature.detect.DetectorHspaDc
import cz.mroczis.netmonster.core.feature.detect.DetectorLteAdvancedNrDisplayInfo
import cz.mroczis.netmonster.core.feature.detect.DetectorLteAdvancedNrServiceState
import cz.mroczis.netmonster.core.feature.detect.DetectorLteAdvancedPhysicalChannel
import cz.mroczis.netmonster.core.model.cell.CellLte
import cz.mroczis.netmonster.core.model.cell.CellNr
import cz.mroczis.netmonster.core.model.cell.ICell
import cz.mroczis.netmonster.core.model.connection.NoneConnection
import cz.mroczis.netmonster.core.model.nr.NrNsaState
import eu.bschmidt.devicepublisher.MainActivity
import eu.bschmidt.devicepublisher.MainApplication
import eu.bschmidt.devicepublisher.model.DataViewModelInterface
import eu.bschmidt.devicepublisher.service.APIService
import eu.bschmidt.devicepublisher.util.DevPubUtils
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import java.util.Collections
import kotlin.random.Random

@Serializable
enum class CellType(val type: String) {
    None("None"),
    LTE("LTE"),
    NR("NR")
}

@Serializable
data class CellData (
    val id: Int? = 0,
    val type: CellType = CellType.None,
    val arfcn: Int = 0,
    val frequency: Int? = 0,
    val band: String = "",
    val rssi: Int? = 0,
    val rsrq: Double? = 0.0,
    val rsrp: Double? = 0.0,
    val estimatedDownBandwidth: Int? = 0,
    val estimatedUpBandwidth: Int? = 0,
)

class CellDataViewModel : ViewModel(), DataViewModelInterface {

    private val _coreConnected: MutableList<CellData> = Collections.synchronizedList(mutableListOf<CellData>())
    private val _coreAvailable: MutableList<CellData> = Collections.synchronizedList(mutableListOf<CellData>())

    private val _connectedCellDataList = MutableLiveData<List<CellData>>()
    val connectedCellDataList: LiveData<List<CellData>> get() = _connectedCellDataList

    private val _availableCellDataList = MutableLiveData<List<CellData>>()
    val availableCellDataList: LiveData<List<CellData>> get() = _availableCellDataList

    init {
        _connectedCellDataList.value = emptyList()
        _availableCellDataList.value = emptyList()
    }

    /* connected */

    private fun setConnectedCellData(newList: List<CellData>) {
        _coreConnected.clear()
        _coreConnected.addAll(newList)
        DevPubUtils.dispatchToMainThread {
            _connectedCellDataList.value = _coreConnected
        }
    }

    fun getConnectedCells(): List<CellData> {
        return _coreConnected.toMutableList()
    }

    /* available */

    private fun setAvailableCellData(newList: List<CellData>) {
        _coreAvailable.clear()
        _coreAvailable.addAll(newList)
        DevPubUtils.dispatchToMainThread {
            _availableCellDataList.value = _coreAvailable
        }
    }

    fun getAvailableCells(): List<CellData> {
        return _coreAvailable.toMutableList()
    }

    /* logic */

    private fun checkPhonePermission(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission( context, Manifest.permission.ACCESS_FINE_LOCATION
              ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                  context,
                  Manifest.permission.ACCESS_COARSE_LOCATION
              ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                  context,
                  Manifest.permission.READ_PHONE_STATE
              ) != PackageManager.PERMISSION_GRANTED
          ) {
              return false
          } else {
              return true
          }
    }

    private fun getCellList(): List<ICell> {
        val appContext = MainApplication.appContext
        val permissionGranted = checkPhonePermission(appContext)
        if (!permissionGranted) {
            return listOf<ICell>()
        }
        lateinit var allCellInfo: List<ICell>
        NetMonsterFactory.getTelephony(appContext).apply {
            allCellInfo = getAllCellInfo()
        }
        return allCellInfo
    }

    private fun getEstimatedBandwidth(): Pair<Int?, Int?> {
        val cm: ConnectivityManager  =
            MainApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        val network: Network? = cm.activeNetwork
        Log.d(TAG, "network: ${network?.toString()}")
        val down = cm.getNetworkCapabilities(network)?.linkDownstreamBandwidthKbps
        val up = cm.getNetworkCapabilities(network)?.linkUpstreamBandwidthKbps
        return Pair(down, up)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun update() {
        var currentCells: List<ICell>
        runBlocking {
            currentCells = getCellList()
        }
        Log.d(TAG, "ServiceContext: ${MainApplication.appContext}")

        val conList: MutableList<CellData> = mutableListOf<CellData>()
        val avaList: MutableList<CellData> = mutableListOf<CellData>()

        currentCells.forEach { cell ->
            if (cell !is CellLte && cell !is CellNr) {
                return@forEach
            }
            var cellData: CellData = CellData()
            val (down, up) = getEstimatedBandwidth()
            when (cell) {
                is  CellLte -> {
                    val lteCell: CellLte = cell
                    cellData = CellData(id = lteCell.cid,
                        type = CellType.LTE,
                        arfcn = lteCell.band!!.downlinkEarfcn,
                        band = lteCell.band!!.name!!,
                        rssi = lteCell.signal.rssi,
                        rsrp = lteCell.signal.rsrp,
                        rsrq = lteCell.signal.rsrq,
                        estimatedDownBandwidth = if (cell.connectionStatus != NoneConnection()) down else null,
                        estimatedUpBandwidth = if (cell.connectionStatus != NoneConnection()) up else null,
                        )
                }
                is  CellNr -> {
                    val nrCell: CellNr = cell
                    cellData = CellData(id = nrCell.pci,
                        type = CellType.NR,
                        arfcn = nrCell.band!!.downlinkArfcn,
                        band = nrCell.band!!.name!!,
                        rssi = nrCell.signal.ssSinr,
                        rsrp = nrCell.signal.ssRsrp?.toDouble(),
                        rsrq = nrCell.signal.ssRsrq?.toDouble(),
                        frequency = nrCell.band!!.downlinkFrequency,
                        estimatedDownBandwidth = if (cell.connectionStatus != NoneConnection()) down else null,
                        estimatedUpBandwidth = if (cell.connectionStatus != NoneConnection()) up else null,
                        )
                }
            }
            if (cell.connectionStatus != NoneConnection()) {
                conList.add(cellData)
            } else {
                avaList.add(cellData)
            }
        }
        setConnectedCellData(conList)
        setAvailableCellData(avaList)
    }

    companion object {
        private const val TAG = "CellDataViewModel"
        @Volatile
        private var instance: CellDataViewModel? = null

        fun getInstance(): CellDataViewModel {
            return instance ?: synchronized(this) {
                instance ?: CellDataViewModel().also { instance = it }
            }
        }
    }
}
