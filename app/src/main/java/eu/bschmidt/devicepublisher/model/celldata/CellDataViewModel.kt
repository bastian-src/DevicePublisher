package eu.bschmidt.devicepublisher.model.celldata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class CellType(val type: String) {
    None("None"),
    LTE("LTE"),
    NR("NR")
}

data class CellData (
    val id: Int = 0,
    val type: CellType = CellType.None,
    val arfcn: Int = 0,
    val band: Int = 0,
    val rssi: Float = 0F,
    val rsrq: Float = 0F,
    val rsrp: Float = 0F,
)

class CellDataViewModel : ViewModel() {
    private val _cellDataList = MutableLiveData<List<CellData>>()
    val cellDataList: LiveData<List<CellData>> get() = _cellDataList

    init {
        _cellDataList.value = emptyList()
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
}
