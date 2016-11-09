package com.esafirm.androidboilerplate.data.local

import com.esafirm.androidboilerplate.data.model.Ribot
import rx.Observable

interface Database {
	fun setRibots(newRibots: Collection<Ribot>): Observable<Ribot>
	fun getRibots(): Observable<List<Ribot>>
}
