package eu.bschmidt.devicepublisher.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import org.json.JSONObject
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread


class DevicePublisherService : Service(), SensorEventListener {

    private val binder = MyBinder()
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    inner class MyBinder : Binder() {
        fun getCurrentLight(): Float {
            return currentLightValue
        }
    }

    private var currentLightValue: Float = 0f

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        makeToast("Service onCreate!")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        startServer()
    }

    private fun makeToast(text: String) {
        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        })
    }

    private fun startServer() {
        makeToast("starting service thread!")
        thread {
            try {
                val serverSocket = ServerSocket(8080)
                while (true) {
                    val clientSocket = serverSocket.accept()
                    val addr = "${clientSocket.localAddress}:${clientSocket.localPort}"
                    makeToast("new client: $addr")
                    handleClientRequest(clientSocket)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun handleClientRequestAsync(clientSocket: Socket) {
        Thread(object : Runnable {
                private lateinit var socket: Socket
                override fun run() {
                    try {
                        var brightness = android.provider.Settings.System.getInt(contentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS)
                        makeToast("Brightness: $brightness")
                        val json = JSONObject()
                        json.put("brightness", brightness)
                        thread {
                            socket.getOutputStream().write(json.toString().toByteArray())
                            socket.close()
                        }
                    } catch (e: Exception) {
                        Log.e("DevicePublisher", "Error getting brightness", e)
                    }
                }

                fun init(socket: Socket): Runnable {
                    this.socket = socket
                    return this
                }
            }.init(clientSocket)
        ).start()
    }

    private fun handleClientRequest(socket: Socket) {
        try {
            var brightness = android.provider.Settings.System.getInt(contentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS)
            val json = JSONObject()
            json.put("screen", brightness)
            json.put("light", currentLightValue)

            val response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: ${json.toString().length}\r\n" +
                    "\r\n" +
                    json.toString()

            socket.getOutputStream().write(response.toByteArray())
            socket.close()
            makeToast("Successfully sent brightness")
        } catch (e: Exception) {
            Log.e("DevicePublisher", "Error getting brightness", e)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            currentLightValue = event.values[0]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
