package eu.bschmidt.devicepublisher.model.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.bschmidt.devicepublisher.model.api.APIStatus

data class APIStatus (
    var running: Boolean = false,
    var address: String = "",
    var requests: Int = 0,
)

class APIStatusViewModel : ViewModel() {
    private val _apiStatus = MutableLiveData<APIStatus>()
    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    init {
        _apiStatus.value = APIStatus()
    }

    fun incrementRequests() {
        val currentStatus = _apiStatus.value ?: APIStatus()
        currentStatus.requests += 1
        _apiStatus.value = currentStatus
    }

    fun setAddress(value: String) {
        val currentStatus = _apiStatus.value ?: APIStatus()
        currentStatus.address = value
        _apiStatus.value = currentStatus
    }

    fun setRunning(value: Boolean) {
        val currentStatus = _apiStatus.value ?: APIStatus()
        currentStatus.running = value
        _apiStatus.value = currentStatus
    }
}
