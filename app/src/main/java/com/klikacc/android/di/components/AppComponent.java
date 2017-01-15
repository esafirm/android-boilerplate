package com.klikacc.android.di.components;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import com.google.gson.Gson;
import com.klikacc.android.di.modules.AppModule;
import com.klikacc.android.di.modules.NetworkModule;
import com.klikacc.android.di.qualifiers.AppContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {

    /* --------------------------------------------------- */
    /* > Singletons */
    /* --------------------------------------------------- */

    @AppContext
    Context context();
    Gson gson();
    NotificationManager notificationManager();

    /* --------------------------------------------------- */
    /* > Initializer */
    /* --------------------------------------------------- */

    class Initializer {
        public static AppComponent make(Application application) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
        }
    }
}
