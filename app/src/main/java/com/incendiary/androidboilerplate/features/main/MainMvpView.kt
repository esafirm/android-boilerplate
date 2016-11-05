package com.incendiary.androidboilerplate.features.main

import com.incendiary.androidboilerplate.data.model.Ribot
import com.incendiary.androidboilerplate.features.common.MvpView

interface MainMvpView : MvpView {

	fun showRibots(ribots: List<Ribot>)
	fun showRibotsEmpty()
	fun showError()

}
