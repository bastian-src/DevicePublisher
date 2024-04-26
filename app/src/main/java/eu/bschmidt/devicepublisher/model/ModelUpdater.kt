package eu.bschmidt.devicepublisher.model

import android.os.Handler
import android.os.Looper
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.util.DevPubUtils
import eu.bschmidt.devicepublisher.util.StoppableThread

class ModelUpdater: Thread(), StoppableThread {

    private var running = true;
    private val viewModels = listOf<DataViewModelInterface>(
        CellDataViewModel.getInstance(),
    )

    public override fun run() {
        while (running) {
            Thread.sleep(UPDATE_INTERVAL_MS)
            
            DevPubUtils.dispatchToMainThread {
                viewModels.forEach { it.update() }
            }
        }
    }

    public override fun onStop() {
        running = false
    }

    public override fun interrupt() {
        running = false
        super.interrupt()
    }

    companion object {
        private const val UPDATE_INTERVAL_MS = 1000L
    }
}
