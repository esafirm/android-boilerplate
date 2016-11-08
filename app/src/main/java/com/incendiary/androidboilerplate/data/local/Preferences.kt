package com.incendiary.androidboilerplate.data.local

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.NoEncryption

object Preferences {

	/* --------------------------------------------------- */
	/* > Setup */
	/* --------------------------------------------------- */

	fun setup(application: Application) {
		Hawk.init(application)
				.setEncryption(NoEncryption())
				.build()
	}
}
