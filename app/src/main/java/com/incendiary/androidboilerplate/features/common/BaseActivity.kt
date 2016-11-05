package com.incendiary.androidboilerplate.features.common

import android.os.Bundle
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import com.incendiary.androidboilerplate.BoilerplateApplication
import com.incendiary.androidboilerplate.di.component.ActivityComponent
import com.incendiary.androidboilerplate.di.component.ConfigPersistentComponent
import com.incendiary.androidboilerplate.di.component.DaggerConfigPersistentComponent
import com.incendiary.androidboilerplate.di.module.ActivityModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
open class BaseActivity : AppCompatActivity() {

	companion object {
		private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
		private val NEXT_ID = AtomicLong(0)
		private val componentMap = LongSparseArray<ConfigPersistentComponent>()
	}

	private val mActivityComponent: ActivityComponent
			by lazy { configPersistentComponent.activityComponent(ActivityModule(this)) }

	private var activityId: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
		// being called after a configuration change.
		activityId = if (savedInstanceState != null) savedInstanceState.getLong(KEY_ACTIVITY_ID)
		else NEXT_ID.andIncrement
	}

	private val configPersistentComponent: ConfigPersistentComponent
		get() {
			componentMap.get(activityId)?.apply {
				Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)
				return componentMap[activityId]
			}

			val configPersistentComponent: ConfigPersistentComponent
			configPersistentComponent = DaggerConfigPersistentComponent.builder()
					.applicationComponent(BoilerplateApplication.component())
					.build()
			componentMap.put(activityId, configPersistentComponent)

			Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)

			return configPersistentComponent
		}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putLong(KEY_ACTIVITY_ID, activityId)
	}

	override fun onDestroy() {
		if (!isChangingConfigurations) {
			Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
			componentMap.remove(activityId)
		}
		super.onDestroy()
	}

	fun activityComponent(): ActivityComponent {
		return mActivityComponent
	}
}
