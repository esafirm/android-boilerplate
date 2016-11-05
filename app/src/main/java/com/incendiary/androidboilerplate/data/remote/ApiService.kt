package com.incendiary.androidboilerplate.data.remote

import com.incendiary.androidboilerplate.data.model.Ribot
import retrofit2.http.GET
import rx.Observable

interface ApiService {

	@GET("ribots") fun getRibots(): Observable<List<Ribot>>
}
