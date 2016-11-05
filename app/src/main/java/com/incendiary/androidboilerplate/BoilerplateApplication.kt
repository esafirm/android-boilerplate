package com.incendiary.androidboilerplate

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.incendiary.androidboilerplate.data.local.Preferences
import com.incendiary.androidboilerplate.di.component.ApplicationComponent
import com.incendiary.androidboilerplate.di.component.DaggerApplicationComponent
import com.incendiary.androidboilerplate.di.module.ApplicationModule
import com.incendiary.androidcommon.AndroidCommon
import com.incendiary.androidcommon.android.ContextProvider
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class BoilerplateApplication : Application() {

	internal var mApplicationComponent: ApplicationComponent? = null

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Fabric.with(this, Crashlytics())
		}

		setupStorage()
		setupAndroidCommon()
	}

	private fun setupStorage() {
		Preferences.setup(this)
	}

	private fun setupAndroidCommon() {
		AndroidCommon.with(this)
				.enableStricMode(BuildConfig.DEBUG)
				.install()
	}

	// Needed to replace the component with a test specific one
	private var component: ApplicationComponent
		get() {
			mApplicationComponent?.apply { return mApplicationComponent!! }
			mApplicationComponent = DaggerApplicationComponent.builder()
					.applicationModule(ApplicationModule(this))
					.build()
			return mApplicationComponent!!
		}
		set(applicationComponent) {
			mApplicationComponent = applicationComponent
		}

	companion object {

		/* --------------------------------------------------- */
		/* > AppComponent */
		/* --------------------------------------------------- */

		fun get(): BoilerplateApplication {
			return ContextProvider.get() as BoilerplateApplication
		}

		fun component(): ApplicationComponent {
			return get().component
		}
	}
}
