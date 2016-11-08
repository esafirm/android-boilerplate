package com.esafirm.androidboilerplate.data.remote

import com.esafirm.androidboilerplate.data.model.Ribot
import retrofit2.http.GET
import rx.Observable

interface ApiService {

	@GET("ribots") fun getRibots(): Observable<List<Ribot>>
}
