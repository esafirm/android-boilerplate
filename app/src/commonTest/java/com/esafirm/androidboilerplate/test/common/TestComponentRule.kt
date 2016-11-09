package com.esafirm.androidboilerplate.test.common

import android.content.Context
import com.esafirm.androidboilerplate.BoilerplateApplication
import com.esafirm.androidboilerplate.data.DataManager
import com.esafirm.androidboilerplate.test.common.injection.component.DaggerTestComponent
import com.esafirm.androidboilerplate.test.common.injection.component.TestComponent
import com.esafirm.androidboilerplate.test.common.injection.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
class TestComponentRule(val context: Context) : TestRule {

	private val testComponent: TestComponent
			by lazy {
				DaggerTestComponent.builder()
						.applicationTestModule(ApplicationTestModule(BoilerplateApplication.get()))
						.build()
			}

	val mockDataManager: DataManager
		get() = testComponent.dataManager()

	override fun apply(base: Statement, description: Description): Statement {
		return object : Statement() {
			@Throws(Throwable::class)
			override fun evaluate() {
				val application = BoilerplateApplication.get()
				application.component = testComponent
				base.evaluate()
//				application.component = null TODO what happen if we comment this?
			}
		}
	}
}
