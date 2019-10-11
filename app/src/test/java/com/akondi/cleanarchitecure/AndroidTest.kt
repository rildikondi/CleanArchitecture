package com.akondi.cleanarchitecure

import android.app.Application
import android.content.Context
import com.akondi.cleanarchitecure.application.AndroidApplication
import com.akondi.cleanarchitecure.core.platform.BaseActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Base class for Android tests. Inherit from it to create test cases which contain android
 * framework dependencies or components.
 *
 * @see UnitTest
 */
@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [21],
    application = AndroidTest.ApplicationStub::class
)
abstract class AndroidTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks =  InjectMocksRule.create(this@AndroidTest)

    fun context(): Context = mock(AndroidApplication::class.java)

    fun activityContext(): Context = mock(BaseActivity::class.java)

    internal class ApplicationStub : Application()
}

