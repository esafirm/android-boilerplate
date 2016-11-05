package com.incendiary.androidboilerplate.di.component

import com.incendiary.androidboilerplate.di.PerActivity
import com.incendiary.androidboilerplate.di.module.ActivityModule
import com.incendiary.androidboilerplate.features.main.MainActivity
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class)) interface ActivityComponent {
	fun inject(mainActivity: MainActivity)
}
