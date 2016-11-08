package com.esafirm.androidboilerplate.util

import rx.Subscription

fun Subscription?.safeUnsubscribe() {
	if (this != null && !this.isUnsubscribed) {
		this.unsubscribe()
	}
}
