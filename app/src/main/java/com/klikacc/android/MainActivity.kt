package com.klikacc.android

import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.klikacc.android.common.AbsAct
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbsAct() {

    private lateinit var router: Router
    private lateinit var secondRouter: Router

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onViewCreated(savedInstanceState: Bundle?) {
        router = Conductor.attachRouter(this, main_container, savedInstanceState)
        secondRouter = Conductor.attachRouter(this, main_overlay_container, savedInstanceState)
    }

    override fun getMainRouter(): Router = router
    override fun getOverlayRouter(): Router = secondRouter
}
