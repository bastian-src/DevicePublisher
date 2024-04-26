package eu.bschmidt.devicepublisher.api.celldata

import eu.bschmidt.devicepublisher.model.celldata.CellData
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.util.DevPubUtils
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.*

const val TAG = "routeCellData"

suspend fun getCellList(viewModel: CellDataViewModel): MutableList<CellData> {
    val localCellList: MutableList<CellData> = mutableListOf<CellData>()
    DevPubUtils.waitForMainThread {
        viewModel.cellDataList.value?.let { cellDataList ->
            localCellList.addAll(cellDataList.toMutableList())
        }
    }
    return localCellList
}

fun Route.routeCellData() {
    val viewModel: CellDataViewModel = CellDataViewModel.getInstance()
    
    route("/celldata") {
        get("/cells") {
            val cellList = getCellList(viewModel)
            call.respond(cellList)
        }

        get("/cell/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val cellList = getCellList(viewModel)
            if (id != null && id >= 0 && id < cellList.size) {
                val someCell: CellData = cellList[id]
                call.respond(someCell)
            } else {
                call.respondText("Cell not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}
