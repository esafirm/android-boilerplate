package com.esafirm.androidboilerplate.features.main

import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.features.common.MvpView

interface MainMvpView : MvpView {

	fun showRibots(ribots: List<Ribot>)
	fun showRibotsEmpty()
	fun showError()

}
