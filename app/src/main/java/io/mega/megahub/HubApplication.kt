package io.mega.megahub

import android.app.Application
import io.mega.megahub.andfix.AndFixPatchManager
import timber.log.Timber

class HubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initAndFix()
    }

    private fun initAndFix() {
        this.applicationContext
        AndFixPatchManager.getInstance().initPatch(this)
    }
}