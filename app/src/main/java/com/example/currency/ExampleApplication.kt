package com.example.currency

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
            StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        }
    }

    companion object {
        private const val TAG = "Application"
    }
}
