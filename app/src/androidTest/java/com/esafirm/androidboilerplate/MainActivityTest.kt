package com.esafirm.androidboilerplate

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.esafirm.androidboilerplate.features.main.MainActivity
import com.esafirm.androidboilerplate.test.common.TestComponentRule
import com.esafirm.androidboilerplate.test.common.TestDataFactory
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import rx.Observable

@RunWith(AndroidJUnit4::class) class MainActivityTest {

	val component = TestComponentRule(InstrumentationRegistry.getTargetContext())
	val main: ActivityTestRule<MainActivity> = object : ActivityTestRule<MainActivity>(MainActivity::class.java, false, false) {
		override fun getActivityIntent(): Intent {
			// Override the default intent so we pass a false flag for syncing so it doesn't
			// start a sync service in the background that would affect  the behaviour of
			// this test.
			return MainActivity.getStartIntent(InstrumentationRegistry.getTargetContext(),
					false)
		}
	}

	// TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
	// in the Application before any Activity is launched.
	@JvmField @Rule val chain: TestRule = RuleChain.outerRule(component).around(main)

	@Test fun listOfRibotsShows() {
		val testDataRibots = TestDataFactory.makeListRibots(20)
		`when`(component.mockDataManager.ribots).thenReturn(Observable.just(testDataRibots))

		main.launchActivity(null)

		var position = 0
		for (ribot in testDataRibots) {
			onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
			val name = String.format("%s %s", ribot.profile.name.first,
					ribot.profile.name.last)
			onView(withText(name)).check(matches(isDisplayed()))
			onView(withText(ribot.profile.email)).check(matches(isDisplayed()))
			position++
		}
	}
}
