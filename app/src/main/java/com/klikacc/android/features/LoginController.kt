package com.klikacc.android.features

import android.view.View
import com.klikacc.android.R
import com.klikacc.android.common.AbsController

class LoginController : AbsController() {

    override fun getLayoutResId(): Int = R.layout.controller_login

    override fun onViewBound(view: View) {
    }
}
