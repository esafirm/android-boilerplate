package com.klikacc.android.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Router
import com.klikacc.android.KlikApp
import com.klikacc.android.di.components.ActivityComponent

abstract class AbsAct : AppCompatActivity(), Setup {

    val component: ActivityComponent by lazy {
        ActivityComponent.Initializer.make(KlikApp.component(), this, getMainRouter(), getOverlayRouter())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        onViewCreated(savedInstanceState)
    }

    /* --------------------------------------------------- */
    /* > Setup */
    /* --------------------------------------------------- */

    override fun onViewCreated(savedInstanceState: Bundle?) {}
    override fun getMainRouter(): Router? = null
    override fun getOverlayRouter(): Router? = null

    /* --------------------------------------------------- */
    /* > Abstract Layout */
    /* --------------------------------------------------- */

    protected abstract fun getLayoutResId(): Int
}
