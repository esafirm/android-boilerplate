package com.klikacc.android.common

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.rxlifecycle.RxController
import com.esafirm.emvipi.AbsPresenter
import com.esafirm.emvipi.view.MvpView
import com.klikacc.android.di.components.ActivityComponent

abstract class AbsController : RxController {

    constructor() {
        onInit()
    }

    constructor(args: Bundle) : super(args) {
        onInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        onSetupComponent()
        val view = inflater.inflate(layoutResId, container, false)
        onViewBound(view)
        return view
    }

    protected fun onInit() {
        // to be overridden
    }

    protected fun onSetupComponent() {
        //to be overridden
    }

    protected abstract val layoutResId: Int

    protected abstract fun onViewBound(view: View)

    /* --------------------------------------------------- */
    /* > Components */
    /* --------------------------------------------------- */

    protected fun getActivityComponent(): ActivityComponent {
        val activity = activity as AbsAct?
                ?: throw IllegalStateException("Activity should not be null at this state. Please use this on #onSetupComponent")
        return activity.component
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    protected fun attachPresenter(mvpView: MvpView, presenter: AbsPresenter<in MvpView>) {
        presenter.attachView(mvpView)
        addLifecycleListener(object : Controller.LifecycleListener() {
            override fun preAttach(controller: Controller, view: View) = presenter.attachView(mvpView)
            override fun postDestroy(controller: Controller) = presenter.detachView()
        })
    }

    protected fun finishActivity() {
        val activity = activity
        activity?.finish()
    }

    protected fun popCurrentController() {
        if (router.backstackSize > 0) {
            router.popCurrentController()
        }
    }

    val supportFragmentManager: FragmentManager?
        get() {
            val appCompatActivity = activity as AppCompatActivity? ?: return null
            return appCompatActivity.supportFragmentManager
        }
}
