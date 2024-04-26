package eu.bschmidt.devicepublisher.util

import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.os.Handler
import android.os.Looper
import android.text.format.Formatter
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.Inet4Address
import java.net.NetworkInterface

object DevPubUtils {
    suspend fun waitForMainThread(block: () -> Unit) {
        return withContext(Dispatchers.Main) {
            block()
        }
    }

    fun dispatchToMainThread(block: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.post { block() }
    }

    fun getIpv4HostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return "127.0.0.1"
    }
}
