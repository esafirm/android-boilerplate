package com.incendiary.androidboilerplate.data

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.IBinder
import com.incendiary.androidboilerplate.BoilerplateApplication
import com.incendiary.androidboilerplate.data.model.Ribot
import com.incendiary.androidboilerplate.util.AndroidComponentUtil
import com.incendiary.androidboilerplate.util.isNetWorkConnected
import rx.Observer
import rx.Subscription
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SyncService : Service() {

	@Inject lateinit var mDataManager: DataManager

	private var mSubscription: Subscription? = null

	override fun onCreate() {
		super.onCreate()
		BoilerplateApplication.component().inject(this)
	}

	override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		Timber.i("Starting sync...")

		if (!isNetWorkConnected()) {
			Timber.i("Sync canceled, connection not available")
			AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable::class.java, true)
			stopSelf(startId)
			return Service.START_NOT_STICKY
		}

		if (mSubscription != null && !mSubscription!!.isUnsubscribed) mSubscription!!.unsubscribe()
		mSubscription = mDataManager!!.syncRibots().subscribeOn(Schedulers.io()).subscribe(object : Observer<Ribot> {
			override fun onCompleted() {
				Timber.i("Synced successfully!")
				stopSelf(startId)
			}

			override fun onError(e: Throwable) {
				Timber.w(e, "Error syncing.")
				stopSelf(startId)
			}

			override fun onNext(ribot: Ribot) {
			}
		})

		return Service.START_STICKY
	}

	override fun onDestroy() {
		if (mSubscription != null) mSubscription!!.unsubscribe()
		super.onDestroy()
	}

	override fun onBind(intent: Intent): IBinder? {
		return null
	}

	class SyncOnConnectionAvailable : BroadcastReceiver() {

		override fun onReceive(context: Context, intent: Intent) {
			if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION && context.isNetWorkConnected()) {
				Timber.i("Connection is now available, triggering sync...")
				AndroidComponentUtil.toggleComponent(context, this.javaClass, false)
				context.startService(getStartIntent(context))
			}
		}
	}

	companion object {

		fun getStartIntent(context: Context): Intent {
			return Intent(context, SyncService::class.java)
		}

		fun isRunning(context: Context): Boolean {
			return AndroidComponentUtil.isServiceRunning(context, SyncService::class.java)
		}
	}
}
