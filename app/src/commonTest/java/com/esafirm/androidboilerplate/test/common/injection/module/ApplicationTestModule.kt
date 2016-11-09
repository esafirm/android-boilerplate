package com.esafirm.androidboilerplate.test.common.injection.module

import android.app.Application
import android.content.Context
import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.data.local.Database
import com.esafirm.androidboilerplate.data.remote.ApiService
import com.esafirm.androidboilerplate.di.ApplicationContext
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module class ApplicationTestModule(private val mApplication: Application) {

	@Provides internal fun provideApplication(): Application {
		return mApplication
	}

	@Provides @ApplicationContext internal fun provideContext(): Context {
		return mApplication
	}

	/* --------------------------------------------------- */
	/* > Mocks */
	/* --------------------------------------------------- */

	@Provides @Singleton internal fun provideDataManager()
			: DataManager = mock(DataManager::class.java)

	@Provides @Singleton internal fun provideRibotsService()
			: ApiService = mock(ApiService::class.java)

	@Provides @Singleton internal fun provideDatabase()
			: Database = mock(Database::class.java)
}
