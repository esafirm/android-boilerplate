package com.esafirm.androidboilerplate.util

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rx.observers.TestSubscriber

class RxEventBusTest {

	private lateinit var mEventBus: RxEventBus

	// Must be added to every test class that targets app code that uses RxJava
	@Rule @JvmField val overrideSchedulerRule = RxSchedulersOverrideRule()

	@Before fun setUp() {
		mEventBus = RxEventBus()
	}

	@Test fun postedObjectsAreReceived() {
		val testSubscriber = TestSubscriber<Any>()
		mEventBus.observable().subscribe(testSubscriber)

		val event1 = Any()
		val event2 = Any()
		mEventBus.post(event1)
		mEventBus.post(event2)

		testSubscriber.assertValues(event1, event2)
	}

	@Test fun filteredObservableOnlyReceivesSomeObjects() {
		val testSubscriber = TestSubscriber<String>()
		mEventBus.filteredObservable(String::class.java).subscribe(testSubscriber)

		val stringEvent = "Event"
		val intEvent = 20
		mEventBus.post(stringEvent)
		mEventBus.post(intEvent)

		testSubscriber.assertValueCount(1)
		testSubscriber.assertValue(stringEvent)
	}
}
