package com.esafirm.androidboilerplate.di.module

import android.app.Application
import android.content.Context
import com.esafirm.androidboilerplate.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Provide application-level dependencies.
 */
@Module class ApplicationModule(val mApplication: Application) {

	@Provides internal fun provideApplication(): Application {
		return mApplication
	}

	@Provides @ApplicationContext internal fun provideContext(): Context {
		return mApplication
	}
}
