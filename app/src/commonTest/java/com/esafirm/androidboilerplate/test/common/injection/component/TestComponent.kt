package com.esafirm.androidboilerplate.test.common.injection.component

import com.esafirm.androidboilerplate.di.component.ApplicationComponent
import com.esafirm.androidboilerplate.test.common.injection.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent
