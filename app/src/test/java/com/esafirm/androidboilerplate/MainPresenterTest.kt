package com.esafirm.androidboilerplate

import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.features.main.MainMvpView
import com.esafirm.androidboilerplate.features.main.MainPresenter
import com.esafirm.androidboilerplate.test.common.TestDataFactory
import com.esafirm.androidboilerplate.util.RxSchedulersOverrideRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.anyListOf
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable

@RunWith(MockitoJUnitRunner::class) class MainPresenterTest {

	@Mock internal lateinit var mockView: MainMvpView
	@Mock internal lateinit var mMockDataManager: DataManager
	private val presenter: MainPresenter
			by lazy { MainPresenter(mMockDataManager) }

	@JvmField @Rule val mOverrideSchedulersRule = RxSchedulersOverrideRule()

	@Before fun setUp() {
		presenter.attachView(mockView)
	}

	@After fun tearDown() {
		presenter.detachView()
	}

	@Test fun loadRibotsReturnsRibots() {
		val ribots = TestDataFactory.makeListRibots(10)
		`when`(mMockDataManager.ribots).thenReturn(Observable.just(ribots))

		presenter.loadRibots()
		verify<MainMvpView>(mockView).showRibots(ribots)
		verify<MainMvpView>(mockView, never()).showRibotsEmpty()
		verify<MainMvpView>(mockView, never()).showError()
	}

	@Test fun loadRibotsReturnsEmptyList() {
		`when`(mMockDataManager.ribots).thenReturn(Observable.just(emptyList<Ribot>()))

		presenter.loadRibots()
		verify<MainMvpView>(mockView).showRibotsEmpty()
		verify<MainMvpView>(mockView, never()).showRibots(anyListOf(Ribot::class.java))
		verify<MainMvpView>(mockView, never()).showError()
	}

	@Test fun loadRibotsFails() {
		`when`(mMockDataManager.ribots).thenReturn(
				Observable.error<List<Ribot>>(RuntimeException()))

		presenter.loadRibots()
		verify<MainMvpView>(mockView).showError()
		verify<MainMvpView>(mockView, never()).showRibotsEmpty()
		verify<MainMvpView>(mockView, never()).showRibots(anyListOf(Ribot::class.java))
	}
}
