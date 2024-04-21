package eu.bschmidt.devicepublisher

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import eu.bschmidt.devicepublisher.databinding.ActivityMainBinding
import eu.bschmidt.devicepublisher.model.celldata.CellData
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.model.celldata.CellType
import eu.bschmidt.devicepublisher.ui.celldata.CellDataFragment
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isServiceRunning = false
    private val viewModel: CellDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener { toggleService() }
        setContentView(binding.root)

        startViewModelUpdateThread()
    }

    private fun startViewModelUpdateThread() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Update ViewModel with random content

                viewModel.cellDataList.value?.forEach { cellData ->
                    viewModel.removeCellData(cellData.id)
                }

                // Add each generated CellData object individually
                repeat(Random.nextInt(1, 10)) {
                    val randomCellData = generateRandomCellData()
                    viewModel.addCellData(randomCellData)
                }

                // Repeat every 5 seconds
                handler.postDelayed(this, 2500)
            }
        }, 2500)
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

    private fun toggleService() {
        isServiceRunning = !isServiceRunning
        updateFabIcon()
    }

    private fun updateFabIcon() {
        val icon = if (isServiceRunning) {
            android.R.drawable.ic_media_pause
        } else {
            android.R.drawable.ic_media_play
        }
        binding.fab.setImageResource(icon)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
