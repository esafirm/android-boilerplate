package com.klikacc.android.extensions

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

fun Router.setRootController(controller: Controller) {
    setRoot(RouterTransaction.with(controller))
}
