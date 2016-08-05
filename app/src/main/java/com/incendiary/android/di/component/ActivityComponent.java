package com.incendiary.android.di.component;

import dagger.Subcomponent;
import com.incendiary.android.di.PerActivity;
import com.incendiary.android.di.module.ActivityModule;
import com.incendiary.android.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity @Subcomponent(modules = ActivityModule.class) public interface ActivityComponent {
  void inject(MainActivity mainActivity);
}
