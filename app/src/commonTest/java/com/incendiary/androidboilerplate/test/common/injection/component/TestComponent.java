package com.incendiary.androidboilerplate.test.common.injection.component;

import com.incendiary.androidboilerplate.di.component.ApplicationComponent;
import com.incendiary.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationTestModule.class) public interface TestComponent
    extends ApplicationComponent {
}
