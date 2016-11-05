package com.incendiary.androidboilerplate.di.component

import android.app.Application
import android.content.Context
import com.incendiary.androidboilerplate.data.DataManager
import com.incendiary.androidboilerplate.data.SyncService
import com.incendiary.androidboilerplate.data.local.DatabaseHelper
import com.incendiary.androidboilerplate.data.remote.ApiService
import com.incendiary.androidboilerplate.di.ApplicationContext
import com.incendiary.androidboilerplate.di.module.ApplicationModule
import com.incendiary.androidboilerplate.di.module.NetworkModule
import com.incendiary.androidboilerplate.util.RxEventBus
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface ApplicationComponent {

	fun inject(syncService: SyncService)

	@ApplicationContext fun context(): Context

	fun application(): Application
	fun apiService(): ApiService
	fun databaseHelper(): DatabaseHelper
	fun dataManager(): DataManager
	fun eventBus(): RxEventBus
}
