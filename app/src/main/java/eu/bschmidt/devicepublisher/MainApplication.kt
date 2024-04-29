package eu.bschmidt.devicepublisher

import android.app.Application
import android.content.Context

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MainApplication.appContext = this
    }

    companion object {
        lateinit var appContext: Context
    }
}