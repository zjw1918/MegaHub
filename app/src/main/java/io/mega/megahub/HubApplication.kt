package io.mega.megahub

import android.app.Application
import timber.log.Timber

class HubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}