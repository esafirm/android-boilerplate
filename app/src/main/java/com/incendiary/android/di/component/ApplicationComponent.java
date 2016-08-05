package com.incendiary.android.di.component;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import com.incendiary.android.data.DataManager;
import com.incendiary.android.data.SyncService;
import com.incendiary.android.data.local.DatabaseHelper;
import com.incendiary.android.data.remote.ApiService;
import com.incendiary.android.di.ApplicationContext;
import com.incendiary.android.di.module.ApplicationModule;
import com.incendiary.android.di.module.NetworkModule;
import com.incendiary.android.util.RxEventBus;

@Singleton @Component(modules = {
    ApplicationModule.class, NetworkModule.class
}) public interface ApplicationComponent {

  void inject(SyncService syncService);

  @ApplicationContext Context context();

  Application application();
  ApiService apiService();
  DatabaseHelper databaseHelper();
  DataManager dataManager();
  RxEventBus eventBus();
}
