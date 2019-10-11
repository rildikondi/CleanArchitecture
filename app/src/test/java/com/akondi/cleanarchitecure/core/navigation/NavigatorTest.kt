package com.akondi.cleanarchitecure.core.navigation

import com.akondi.cleanarchitecure.AndroidTest
import com.akondi.cleanarchitecure.login.Authenticator
import com.akondi.cleanarchitecure.login.LoginActivity
import com.akondi.cleanarchitecure.merchants.presentation.activities.MerchantsActivity
import com.akondi.cleanarchitecure.shouldNavigateTo
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Mock
    private lateinit var authenticator: Authenticator

    @Before
    fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test
    fun `should forward user to login screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(activityContext())

        Mockito.verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test
    fun `should forward user to merchants screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(activityContext())

        Mockito.verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MerchantsActivity::class
    }
}