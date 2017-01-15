package com.klikacc.android.common

import android.os.Bundle

import com.bluelinelabs.conductor.Router

interface Setup {
    fun onViewCreated(savedInstanceState: Bundle?)
    val mainRouter: Router
    val overlayRouter: Router
}
