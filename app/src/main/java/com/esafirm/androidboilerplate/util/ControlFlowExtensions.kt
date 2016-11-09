package com.esafirm.androidboilerplate.util

fun Int.loop(func: (Int) -> Unit) {
	for (i in 0..this - 1) {
		func(i)
	}
}
