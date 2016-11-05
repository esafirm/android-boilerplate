package com.incendiary.androidboilerplate.features.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.incendiary.androidboilerplate.R
import com.incendiary.androidboilerplate.data.SyncService
import com.incendiary.androidboilerplate.data.model.Ribot
import com.incendiary.androidboilerplate.features.common.BaseActivity
import com.incendiary.androidboilerplate.util.DialogFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {

	@Inject internal var presenter: MainPresenter? = null
	@Inject internal var adapter: RibotsAdapter? = null

	@BindView(R.id.recycler_view) internal var mRecyclerView: RecyclerView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activityComponent().inject(this)
		setContentView(R.layout.activity_main)
		ButterKnife.bind(this)

		mRecyclerView!!.adapter = adapter
		mRecyclerView!!.layoutManager = LinearLayoutManager(this)
		presenter!!.attachView(this)
		presenter!!.loadRibots()

		if (intent.getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
			startService(SyncService.getStartIntent(this))
		}
	}

	override fun onDestroy() {
		super.onDestroy()

		presenter!!.detachView()
	}

	override fun showError() {
		DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots)).show()
	}

	override fun showRibotsEmpty() {
		adapter!!.setRibots(emptyList<Ribot>())
		adapter!!.notifyDataSetChanged()
		Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show()
	}

	override fun showRibots(ribots: List<Ribot>) {
		adapter!!.setRibots(ribots)
		adapter!!.notifyDataSetChanged()
	}

	companion object {

		private val EXTRA_TRIGGER_SYNC_FLAG = "uk.co.ribot.androidboilerplate.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG"

		/**
		 * Return an Intent to start this Activity.
		 * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
		 * only be set to false during testing.
		 */
		fun getStartIntent(context: Context, triggerDataSyncOnCreate: Boolean): Intent {
			val intent = Intent(context, MainActivity::class.java)
			intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate)
			return intent
		}
	}
}
