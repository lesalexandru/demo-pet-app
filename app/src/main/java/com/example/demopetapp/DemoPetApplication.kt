package com.example.demopetapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoPetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
