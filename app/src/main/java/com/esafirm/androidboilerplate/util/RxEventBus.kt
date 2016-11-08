package com.esafirm.androidboilerplate.util

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A simple event bus built with RxJava
 */
@Singleton class RxEventBus
@Inject constructor() {

	private val mBusSubject: SerializedSubject<Any, Any>
			by lazy { SerializedSubject<Any, Any>(PublishSubject.create()) }

	/**
	 * Posts an object (usually an Event) to the bus
	 */
	fun post(event: Any) {
		mBusSubject.onNext(event)
	}

	/**
	 * Observable that will emmit everything posted to the event bus.
	 */
	fun observable(): Observable<Any> {
		return mBusSubject
	}

	/**
	 * Observable that only emits events of a specific class.
	 * Use this if you only want to subscribe to one type of events.
	 */
	@SuppressWarnings("unchecked") fun <T> filteredObservable(eventClass: Class<T>): Observable<T> {
		return mBusSubject.ofType(eventClass).cast(eventClass)
	}
}
