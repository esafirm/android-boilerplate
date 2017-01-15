package com.klikacc.android.data;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

public class Preferences {

    public static void init(Application application, boolean enableLog) {
        Hawk.init(application)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(application))
                .setLogLevel(enableLog ? LogLevel.FULL : LogLevel.NONE)
                .build();
    }
}
