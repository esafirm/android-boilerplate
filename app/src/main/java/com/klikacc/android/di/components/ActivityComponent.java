package com.klikacc.android.di.components;

import com.bluelinelabs.conductor.Router;
import com.klikacc.android.common.AbsAct;
import com.klikacc.android.di.ActivityScope;
import com.klikacc.android.di.modules.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    /* --------------------------------------------------- */
    /* > Controller */
    /* --------------------------------------------------- */

    /* --------------------------------------------------- */
    /* > Initializer */
    /* --------------------------------------------------- */

    class Initializer {
        public static ActivityComponent make(AppComponent appComponent, AbsAct activity,
                                             Router router, Router overlayRouter) {
            return DaggerActivityComponent.builder()
                    .appComponent(appComponent)
                    .activityModule(new ActivityModule(activity, router, overlayRouter))
                    .build();
        }
    }
}
