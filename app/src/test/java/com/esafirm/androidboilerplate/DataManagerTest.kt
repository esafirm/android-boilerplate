package com.esafirm.androidboilerplate

import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.data.local.Database
import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.data.remote.ApiService
import com.esafirm.androidboilerplate.test.common.TestDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.anyListOf
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable
import rx.observers.TestSubscriber

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner::class) class DataManagerTest {

	@Mock lateinit var mockDatabase: Database
	@Mock lateinit var mockApiService: ApiService
	private lateinit var dataManager: DataManager

	@Before fun setUp() {
		dataManager = DataManager(mockApiService, mockDatabase)
	}

	@Test fun syncRibotsEmitsValues() {
		val ribots = arrayListOf(
				TestDataFactory.makeRibot("r1"),
				TestDataFactory.makeRibot("r2")
		)
		stubSyncRibotsHelperCalls(ribots)

		val result = TestSubscriber<Ribot>()
		dataManager.syncRibots().subscribe(result)
		result.assertNoErrors()
		result.assertReceivedOnNext(ribots)
	}

	@Test fun syncRibotsCallsApiAndDatabase() {
		val ribots = arrayListOf(
				TestDataFactory.makeRibot("r1"),
				TestDataFactory.makeRibot("r2")
		)
		stubSyncRibotsHelperCalls(ribots)

		dataManager.syncRibots().subscribe()
		// Verify right calls to helper methods
		verify<ApiService>(mockApiService).getRibots()
		verify<Database>(mockDatabase).setRibots(ribots)
	}

	@Test fun syncRibotsDoesNotCallDatabaseWhenApiFails() {
		`when`(mockApiService.getRibots()).thenReturn(
				Observable.error<List<Ribot>>(RuntimeException()))

		dataManager.syncRibots().subscribe(TestSubscriber<Ribot>())
		// Verify right calls to helper methods
		verify<ApiService>(mockApiService).getRibots()
		verify<Database>(mockDatabase, never()).setRibots(anyListOf(Ribot::class.java))
	}

	private fun stubSyncRibotsHelperCalls(ribots: List<Ribot>) {
		// Stub calls to the ribot service and database helper.
		`when`(mockApiService.getRibots()).thenReturn(Observable.just(ribots))
		`when`(mockDatabase.setRibots(ribots)).thenReturn(Observable.from(ribots))
	}
}
