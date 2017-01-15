package com.klikacc.android.di.modules;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.bluelinelabs.conductor.Router;
import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.DefaultCameraModule;
import com.klikacc.android.common.AbsAct;
import com.klikacc.android.di.ActivityScope;
import com.klikacc.android.di.qualifiers.ActivityContext;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class ActivityModule {

    private final AbsAct defaultActivity;
    private final Router router;
    private final Router overlayRouter;

    public ActivityModule(AbsAct defaultActivity, Router router, Router overlaRouter) {
        this.defaultActivity = defaultActivity;
        this.router = router;
        this.overlayRouter = overlaRouter;
    }

    @ActivityContext
    @ActivityScope
    @Provides
    Context provideContext() {
        return defaultActivity;
    }

    @ActivityScope
    @Provides
    Activity provideActivity() {
        return defaultActivity;
    }

    @ActivityScope
    @Provides
    FragmentManager provideFragmentManager() {
        return defaultActivity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    @Named("main")
    Router provideRouter() {
        return router;
    }

    @ActivityScope
    @Provides
    @Named("overlay")
    Router provideOverlayRouter() {
        return overlayRouter;
    }

    @Provides
    ProgressDialog provideProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(defaultActivity);
        progressDialog.setMessage("Please wait…");
        return progressDialog;
    }

    @Provides
    CameraModule provideCameraModule() {
        return new DefaultCameraModule();
    }
}
