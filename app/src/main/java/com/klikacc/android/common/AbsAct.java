package com.klikacc.android.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Router;
import com.klikacc.android.KlikApp;
import com.klikacc.android.di.components.ActivityComponent;

public abstract class AbsAct extends AppCompatActivity implements Setup {

    private ActivityComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        onViewCreated(savedInstanceState);
    }

    @NonNull
    public ActivityComponent getComponent() {
        if (component == null) {
            component = ActivityComponent.Initializer.make(
                    KlikApp.Companion.component(), this, getMainRouter(), getOverlayRouter());
        }
        return component;
    }

    /* --------------------------------------------------- */
    /* > Setup */
    /* --------------------------------------------------- */

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
    }

    @Override
    public Router getMainRouter() {
        return null;
    }

    public Router getOverlayRouter() {
        return null;
    }

    /* --------------------------------------------------- */
    /* > Abstract Layout */
    /* --------------------------------------------------- */

    @LayoutRes
    protected abstract int getLayoutResId();
}
