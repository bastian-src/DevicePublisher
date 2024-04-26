package eu.bschmidt.devicepublisher

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import eu.bschmidt.devicepublisher.service.APIService
import eu.bschmidt.devicepublisher.databinding.ActivityMainBinding
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.model.api.APIStatusViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val cellDataViewModel: CellDataViewModel = CellDataViewModel.getInstance()
    private val apiStatusViewModel: APIStatusViewModel = APIStatusViewModel.getInstance()

    // TODO: Change the service running state to an enum to include "Loading"
    private var isServiceRunning: Boolean = false
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            // if permission was denied, the service can still run only the notification won't be visible
        }
    private var apiService: APIService? = null
    private var serviceBoundState by mutableStateOf(false)
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d(TAG, "onServiceConnected")

            val binder = service as APIService.LocalBinder
            apiService = binder.getService()
            serviceBoundState = true
            isServiceRunning = true

            onServiceConnected()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            // This is called when the connection with the service has been disconnected. Clean up.
            Log.d(TAG, "onServiceDisconnected")

            isServiceRunning = false
            serviceBoundState = false
            apiService = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener { toggleService() }
        setContentView(binding.root)

        checkAndRequestNotificationPermission()
        tryToBindToServiceIfRunning()
        initUI()
    }

    private fun initUI() {
        apiStatusViewModel.apiStatus.observe(this) { status ->
            isServiceRunning = status.running
            updateFabIcon()
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)) {
                android.content.pm.PackageManager.PERMISSION_GRANTED -> {
                    // permission already granted
                }

                else -> {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun tryToBindToServiceIfRunning() {
        Intent(this, APIService::class.java).also { intent ->
            bindService(intent, connection, 0)
        }
    }

    private fun startAPIService() {
        val serviceIntent = Intent(this, APIService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
        tryToBindToServiceIfRunning()
    }

    private fun stopAPIService() {
        apiService?.stopForegroundService()
    }

    private fun onServiceConnected() {
        Toast.makeText(this, "onServiceConnected", Toast.LENGTH_SHORT).show()
    }

    private fun toggleService() {
        if (!serviceBoundState) {
            startAPIService()
        } else {
            stopAPIService()
        }
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
