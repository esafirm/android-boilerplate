package com.esafirm.androidboilerplate.di.component

import com.esafirm.androidboilerplate.di.PerActivity
import com.esafirm.androidboilerplate.di.module.ActivityModule
import com.esafirm.androidboilerplate.features.main.MainActivity
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class)) interface ActivityComponent {
	fun inject(mainActivity: MainActivity)
}
