package com.klikacc.android

import android.app.Application
import android.content.Context
import com.klikacc.android.data.Preferences
import com.klikacc.android.di.components.AppComponent
import timber.log.Timber

class KlikApp : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        fun appContext(): Context {
            return appComponent.context()
        }

        fun component(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupPreference()
        setupFont()
        setupTimber()
    }

    private fun setupComponent() {
        AppComponent.Initializer.make(this)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupPreference() {
        Preferences.init(this, BuildConfig.DEBUG)
    }

    private fun setupFont() {}
}
