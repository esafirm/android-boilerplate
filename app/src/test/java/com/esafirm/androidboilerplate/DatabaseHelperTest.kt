package com.esafirm.androidboilerplate

import com.esafirm.androidboilerplate.data.local.DatabaseHelper
import com.esafirm.androidboilerplate.data.local.Db
import com.esafirm.androidboilerplate.data.local.DbOpenHelper
import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.test.common.TestDataFactory
import com.esafirm.androidboilerplate.util.DefaultConfig
import com.esafirm.androidboilerplate.util.RxSchedulersOverrideRule
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import rx.observers.TestSubscriber
import java.util.*

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class DatabaseHelperTest {

	private val mDatabaseHelper = DatabaseHelper(DbOpenHelper(RuntimeEnvironment.application))

	@JvmField @Rule val mOverrideSchedulersRule = RxSchedulersOverrideRule()

	@Test fun setRibots() {
		val ribot1 = TestDataFactory.makeRibot("r1")
		val ribot2 = TestDataFactory.makeRibot("r2")
		val ribots = Arrays.asList(ribot1, ribot2)

		val result = TestSubscriber<Ribot>()
		mDatabaseHelper.setRibots(ribots).subscribe(result)
		result.assertNoErrors()
		result.assertReceivedOnNext(ribots)

		val cursor = mDatabaseHelper.briteDb.query("SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME)
		assertEquals(2, cursor.count)

		for (ribot in ribots) {
			cursor.moveToNext()
			assertEquals(ribot.profile, Db.RibotProfileTable.parseCursor(cursor))
		}
	}

	@Test fun getRibots() {
		val ribot1 = TestDataFactory.makeRibot("r1")
		val ribot2 = TestDataFactory.makeRibot("r2")
		val ribots = Arrays.asList(ribot1, ribot2)

		mDatabaseHelper.setRibots(ribots).subscribe()

		val result = TestSubscriber<List<Ribot>>()
		mDatabaseHelper.getRibots().subscribe(result)
		result.assertNoErrors()
		result.assertValue(ribots)
	}
}
