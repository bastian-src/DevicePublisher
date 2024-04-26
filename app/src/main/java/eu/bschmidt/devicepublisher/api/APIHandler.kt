package eu.bschmidt.devicepublisher.api

import eu.bschmidt.devicepublisher.api.celldata.routeCellData
import eu.bschmidt.devicepublisher.model.api.APIStatusViewModel
import eu.bschmidt.devicepublisher.util.DevPubUtils
import eu.bschmidt.devicepublisher.util.StoppableThread
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

class APIHandler : Thread(), StoppableThread {
    // Create API ViewModel to share stats (status, address:port, and number of API calls since start)

    private var running = true;
    private val apiStatusViewModel = APIStatusViewModel.getInstance()

    private lateinit var server: NettyApplicationEngine

    public override fun run() {
        init()
        while (running) {
            Thread.sleep(UPDATE_INTERVAL_MS)
        }
    }

    private fun init() {
        val addr = DevPubUtils.getIpv4HostAddress() + ":$API_PORT"
        DevPubUtils.dispatchToMainThread {
            apiStatusViewModel.setRunning(true)
            apiStatusViewModel.setAddress(addr)
            // TODO: Set address here too
        }
        server = embeddedServer(Netty, port = API_PORT, module = Application::module)
        server.start()
    }

    public override fun onStop() {
        DevPubUtils.dispatchToMainThread {
            apiStatusViewModel.setRunning(false)
        }
        running = false
        server.stop(0, 0)
    }

    public override fun interrupt() {
        onStop()
        super.interrupt()
    }

    companion object {
        private const val UPDATE_INTERVAL_MS = 500L
        private const val API_PORT = 7353
    }
}

fun Application.module() {
    this.install(countRequestsPlugin)
    this.install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    routing {
        route("/api/v1") {
            get("") {
                call.respondText("Hello World!")
            }
            // Add routes here
            routeCellData()
        }
    }
}

val countRequestsPlugin = createApplicationPlugin("countRequestsPlugin") {
    onCall { _ ->
        DevPubUtils.dispatchToMainThread {
            APIStatusViewModel.getInstance().incrementRequests()
        }
    }
}
