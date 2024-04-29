package eu.bschmidt.devicepublisher.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder

import android.os.Binder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import eu.bschmidt.devicepublisher.model.ModelUpdater
import eu.bschmidt.devicepublisher.model.api.APIStatusViewModel
import eu.bschmidt.devicepublisher.api.APIHandler

class APIService : Service(), LifecycleOwner {
    private val binder = LocalBinder()
    private val mDispatcher = ServiceLifecycleDispatcher(this)
    private var updaterThread: ModelUpdater? = null
    private var apiThread: APIHandler? = null

    override val lifecycle: Lifecycle = mDispatcher.lifecycle

    inner class LocalBinder : Binder() {
        fun getService(): APIService = this@APIService
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind")
        return binder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        startAsForegroundService()
        // startAPIServer()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        APIService.serviceContext = this
        Toast.makeText(this, "Foreground Service created", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        Toast.makeText(this, "Foreground Service destroyed", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startAsForegroundService() {
        // create the notification channel
        NotificationsHelper.createNotificationChannel(this)

        // promote service to foreground service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                NOTIFICATION_ID,
                NotificationsHelper.buildNotification(this), ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION)

        } else {
            startForeground(NOTIFICATION_ID, NotificationsHelper.buildNotification(this),)
        }

        // start updater thread
        updaterThread = ModelUpdater()
        updaterThread?.start()
        apiThread = APIHandler()
        apiThread?.start()

        Log.d(TAG, "startAsForegroundService()")
    }

    fun stopForegroundService() {
        apiThread?.onStop()
        apiThread?.join()
        updaterThread?.onStop()
        updaterThread?.join()
        stopSelf()
    }

    companion object {
        private const val TAG = "APIService"
        private const val NOTIFICATION_ID = 123
        lateinit var serviceContext: Context
    }

}
