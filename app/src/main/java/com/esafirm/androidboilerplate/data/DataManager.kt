package com.esafirm.androidboilerplate.data

import com.esafirm.androidboilerplate.data.local.DatabaseHelper
import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.data.remote.ApiService
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class DataManager
@Inject constructor(private val mApiService: ApiService, private val databaseHelper: DatabaseHelper) {

	fun syncRibots(): Observable<Ribot> {
		return mApiService.getRibots().concatMap { databaseHelper.setRibots(it) }
	}

	val ribots: Observable<List<Ribot>>
		get() = databaseHelper.ribots.distinct()
}
