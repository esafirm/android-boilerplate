package com.esafirm.androidboilerplate.util

import android.content.Context
import android.net.ConnectivityManager
import retrofit2.adapter.rxjava.HttpException

/**
 * Returns true if the Throwable is an instance of RetrofitError with an
 * http status code equals to the given one.
 */
fun Throwable.isHttpStatusCode(statusCode: Int): Boolean {
	return this is HttpException && this.code() == statusCode
}

fun Context.isNetWorkConnected(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	val activeNetwork = cm.activeNetworkInfo
	return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}
