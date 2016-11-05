package com.incendiary.androidboilerplate.di.component

import com.incendiary.androidboilerplate.di.ConfigPersistent
import com.incendiary.androidboilerplate.di.module.ActivityModule
import com.incendiary.androidboilerplate.features.common.BaseActivity
import dagger.Component

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check [BaseActivity] to see how this components
 * survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
	fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
