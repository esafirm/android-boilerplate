package com.klikacc.android.common

import android.os.Bundle

import com.bluelinelabs.conductor.Router

interface Setup {
    fun onViewCreated(savedInstanceState: Bundle?)
    fun getMainRouter(): Router?
    fun getOverlayRouter(): Router?
}
