package com.klikacc.android.data

import android.app.Application

import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import com.orhanobut.hawk.LogLevel

object Preferences {

    fun init(application: Application, enableLog: Boolean) {
        Hawk.init(application)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(application))
                .setLogLevel(if (enableLog) LogLevel.FULL else LogLevel.NONE)
                .build()
    }
}
