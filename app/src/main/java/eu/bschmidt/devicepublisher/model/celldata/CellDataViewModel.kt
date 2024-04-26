package eu.bschmidt.devicepublisher.model.celldata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.bschmidt.devicepublisher.model.DataViewModelInterface
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
enum class CellType(val type: String) {
    None("None"),
    LTE("LTE"),
    NR("NR")
}

@Serializable
data class CellData (
    val id: Int = 0,
    val type: CellType = CellType.None,
    val arfcn: Int = 0,
    val band: Int = 0,
    val rssi: Float = 0F,
    val rsrq: Float = 0F,
    val rsrp: Float = 0F,
)

class CellDataViewModel : ViewModel(), DataViewModelInterface {

    private val _cellDataList = MutableLiveData<List<CellData>>()
    val cellDataList: LiveData<List<CellData>> get() = _cellDataList

    init {
        _cellDataList.value = emptyList()
    }

    fun getCellDataListSize(): Int {
        return _cellDataList.value.orEmpty().size
    }

    fun addCellData(cellData: CellData) {
        val currentList = _cellDataList.value.orEmpty().toMutableList()
        currentList.add(cellData)
        _cellDataList.value = currentList
    }

    fun removeCellData(id: Int) {
        val currentList = _cellDataList.value.orEmpty().toMutableList()
        val index = currentList.indexOfFirst { it.id == id }
        if (index != -1) {
            currentList.removeAt(index)
            _cellDataList.value = currentList
        }
    }

    fun selectedCellFromList(id: Int): LiveData<CellData>? {
        val list = cellDataList.value ?: throw IllegalStateException("Cell list is null")
        for (cell in list) {
            if (cell.id == id) {
                return MutableLiveData(cell)
            }
        }
        throw NoSuchElementException("No CellData found with ID: $id")
    }


    override fun update() {
        // TODO: Get data from cellmonster-core
        _cellDataList.value?.forEach { cellData ->
            removeCellData(cellData.id)
        }

        repeat(Random.nextInt(1, 10)) {
            val randomCellData = generateRandomCellData()
            addCellData(randomCellData)
        }
    }

    private fun generateRandomCellData(): CellData {
        val cellId = Random.nextInt(1, 100)
        val cellType = CellType.values().random()
        val arfcn = Random.nextInt(1000, 2000)
        val band = Random.nextInt(1, 10)
        val rssi = Random.nextFloat() * 100
        val rsrq = Random.nextFloat() * 100
        val rsrp = Random.nextFloat() * 100
        return CellData(cellId, cellType, arfcn, band, rssi, rsrq, rsrp)
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
