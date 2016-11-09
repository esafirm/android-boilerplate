package com.esafirm.androidboilerplate.di.component

import android.app.Application
import android.content.Context
import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.data.SyncService
import com.esafirm.androidboilerplate.data.local.Database
import com.esafirm.androidboilerplate.data.remote.ApiService
import com.esafirm.androidboilerplate.di.ApplicationContext
import com.esafirm.androidboilerplate.di.module.ApplicationModule
import com.esafirm.androidboilerplate.di.module.DataModule
import com.esafirm.androidboilerplate.di.module.NetworkModule
import com.esafirm.androidboilerplate.util.RxEventBus
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DataModule::class))
interface ApplicationComponent {

	fun inject(syncService: SyncService)

	@ApplicationContext fun context(): Context

	fun application(): Application
	fun apiService(): ApiService
	fun databaseHelper(): Database
	fun dataManager(): DataManager
	fun eventBus(): RxEventBus
}
