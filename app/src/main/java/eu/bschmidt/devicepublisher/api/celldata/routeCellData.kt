package eu.bschmidt.devicepublisher.api.celldata

import eu.bschmidt.devicepublisher.model.celldata.CellData
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.util.DevPubUtils
import eu.bschmidt.devicepublisher.api.celldata.getAllCells
import eu.bschmidt.devicepublisher.api.celldata.getConnectedCells
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.*

const val TAG = "routeCellData"

suspend fun getAllCells(viewModel: CellDataViewModel): MutableList<CellData> {
    val localCellList: MutableList<CellData> = mutableListOf<CellData>()
    localCellList.addAll(getConnectedCells(viewModel))
    localCellList.addAll(getAvailableCells(viewModel))
    return localCellList
}

suspend fun getConnectedCells(viewModel: CellDataViewModel): MutableList<CellData> {
    val localCellList: MutableList<CellData> = mutableListOf<CellData>()
    localCellList.addAll(viewModel.getConnectedCells().toMutableList())
    return localCellList
}

suspend fun getAvailableCells(viewModel: CellDataViewModel): MutableList<CellData> {
    val localCellList: MutableList<CellData> = mutableListOf<CellData>()
    localCellList.addAll(viewModel.getAvailableCells().toMutableList())
    return localCellList
}

fun Route.routeCellData() {
    val viewModel: CellDataViewModel = CellDataViewModel.getInstance()
    
    route("/celldata") {
        get("/all") {
            val cellList = getAllCells(viewModel)
            call.respond(cellList)
        }
        
        route("/connected") {
            get("/all") {
                val cellList = getConnectedCells(viewModel)
                call.respond(cellList)
            }
            get("/cell/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val cellList = getConnectedCells(viewModel)
                if (id != null && id >= 0 && id < cellList.size) {
                    val someCell: CellData = cellList[id]
                    call.respond(someCell)
                } else {
                    call.respondText("Cell not found", status = HttpStatusCode.NotFound)
                }
            }
        }

        route("/available") {
            get("/all") {
                val cellList = getAvailableCells(viewModel)
                call.respond(cellList)
            }
            get("/cell/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val cellList = getAvailableCells(viewModel)
                if (id != null && id >= 0 && id < cellList.size) {
                    val someCell: CellData = cellList[id]
                    call.respond(someCell)
                } else {
                    call.respondText("Cell not found", status = HttpStatusCode.NotFound)
                }
            }
        }
        
    }
}
