package com.klikacc.android.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.rxlifecycle.RxController;
import com.esafirm.emvipi.AbsPresenter;
import com.esafirm.emvipi.view.MvpView;
import com.klikacc.android.di.components.ActivityComponent;

public abstract class AbsController extends RxController {

    public AbsController() {
        onInit();
    }

    public AbsController(Bundle args) {
        super(args);
        onInit();
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        onSetupComponent();
        View view = inflater.inflate(getLayoutResId(), container, false);
        onViewBound(view);
        return view;
    }

    protected void onInit() {
        // to be overrided
    }

    protected void onSetupComponent() {
        //to be overrided
    }

    protected abstract int getLayoutResId();

    protected abstract void onViewBound(View view);

    /* --------------------------------------------------- */
    /* > Components */
    /* --------------------------------------------------- */

    public ActivityComponent getActivityComponent() {
        AbsAct activity = (AbsAct) getActivity();
        if (activity == null) {
            throw new IllegalStateException("Activity should not be null at this state. Please use this on #onSetupComponent");
        }
        return activity.getComponent();
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    @SuppressWarnings("unchecked")
    protected void attachPresenter(final MvpView mvpView, final AbsPresenter presenter) {
        presenter.attachView(mvpView);

        addLifecycleListener(new LifecycleListener() {
            @Override
            public void preAttach(@NonNull Controller controller, @NonNull View view) {
                super.preAttach(controller, view);
                presenter.attachView(mvpView);
            }

            @Override
            public void postDestroy(@NonNull Controller controller) {
                super.postDestroy(controller);
                presenter.detachView();
            }
        });
    }

    protected void finishActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    protected void popCurrentController() {
        if (getRouter().getBackstackSize() > 0) {
            getRouter().popCurrentController();
        }
    }

    @Nullable
    public FragmentManager getSupportFragmentManager() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity == null) {
            return null;
        }
        return appCompatActivity.getSupportFragmentManager();
    }
}
