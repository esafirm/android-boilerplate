package com.incendiary.android.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.incendiary.android.di.component.ApplicationComponent;
import com.incendiary.android.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
