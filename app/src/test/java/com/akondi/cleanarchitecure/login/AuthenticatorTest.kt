package com.akondi.cleanarchitecure.login

import com.akondi.cleanarchitecure.UnitTest
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

    private val authenticator = Authenticator()

    @Test
    fun `returns default value`() {
        authenticator.userLoggedIn() shouldBe true
    }
}