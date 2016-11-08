package com.esafirm.androidboilerplate.features.main

import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.di.ConfigPersistent
import com.esafirm.androidboilerplate.features.common.BasePresenter
import com.esafirm.androidboilerplate.util.safeUnsubscribe
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent class MainPresenter
@Inject constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

	private var mSubscription: Subscription? = null

	override fun attachView(mvpView: MainMvpView) {
		super.attachView(mvpView)
	}

	override fun detachView() {
		super.detachView()
		if (mSubscription != null) mSubscription!!.unsubscribe()
	}

	fun loadRibots() {
		checkViewAttached()

		mSubscription.safeUnsubscribe()
		mSubscription = mDataManager.ribots
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						{
							if (it.isEmpty()) {
								mvpView?.showRibotsEmpty()
							} else {
								mvpView?.showRibots(it)
							}
						},
						{ mvpView?.showError() }
				)
	}
}
