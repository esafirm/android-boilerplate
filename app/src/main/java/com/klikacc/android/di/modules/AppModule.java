package com.klikacc.android.di.modules;

import android.app.NotificationManager;
import android.content.Context;

import com.klikacc.android.di.qualifiers.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @AppContext
    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    NotificationManager provideNotificationManager() {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
