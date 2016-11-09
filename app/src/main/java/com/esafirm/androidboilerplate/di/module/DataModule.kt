package com.esafirm.androidboilerplate.di.module

import com.esafirm.androidboilerplate.data.local.Database
import com.esafirm.androidboilerplate.data.local.DatabaseHelper
import com.esafirm.androidboilerplate.data.local.DbOpenHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class DataModule {
	@Singleton @Provides fun provideDatabseHelper(dbOpenHelper: DbOpenHelper)
			: Database = DatabaseHelper(dbOpenHelper)
}
