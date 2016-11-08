package com.incendiary.androidboilerplate.data.local

import android.app.Application
import com.orhanobut.hawk.Hawk

object Preferences {

	/* --------------------------------------------------- */
	/* > Setup */
	/* --------------------------------------------------- */

	fun setup(application: Application) {
		Hawk.init(application).build()
	}
}
