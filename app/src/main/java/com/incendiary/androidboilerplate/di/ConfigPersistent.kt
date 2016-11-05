package com.incendiary.androidboilerplate.di

import com.incendiary.androidboilerplate.di.component.ConfigPersistentComponent
import javax.inject.Scope

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * [ConfigPersistentComponent]
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigPersistent
