package com.esafirm.androidboilerplate.test.common.injection.module;

import android.app.Application;
import android.content.Context;
import com.esafirm.androidboilerplate.data.DataManager;
import com.esafirm.androidboilerplate.data.remote.ApiService;
import com.esafirm.androidboilerplate.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module public class ApplicationTestModule {

  private final Application mApplication;

  public ApplicationTestModule(Application application) {
    mApplication = application;
  }

  @Provides Application provideApplication() {
    return mApplication;
  }

  @Provides @ApplicationContext Context provideContext() {
    return mApplication;
  }

  /* --------------------------------------------------- */
  /* > Mocks */
  /* --------------------------------------------------- */

  @Provides @Singleton DataManager provideDataManager() {
    return mock(DataManager.class);
  }

  @Provides @Singleton ApiService provideRibotsService() {
    return mock(ApiService.class);
  }
}
