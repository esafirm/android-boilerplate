package com.klikacc.android.features

import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.klikacc.android.R
import com.klikacc.android.common.AbsAct
import com.klikacc.android.extensions.setRootController
import kotlinx.android.synthetic.main.activity_common.*

class LoginAct : AbsAct() {

    lateinit var router: Router

    override fun getLayoutResId(): Int = R.layout.activity_common

    override fun onViewCreated(savedInstanceState: Bundle?) {
        router = Conductor.attachRouter(this, common_container, savedInstanceState)
        router.hasRootController().let {
            router.setRootController(LoginController())
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
