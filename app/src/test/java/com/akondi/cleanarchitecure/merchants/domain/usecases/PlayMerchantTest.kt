package com.akondi.cleanarchitecure.merchants.domain.usecases

import com.akondi.cleanarchitecure.AndroidTest
import com.akondi.cleanarchitecure.core.navigation.Navigator
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PlayMerchantTest : AndroidTest() {

    private val VIDEO_URL = "https://www.youtube.com/watch?v=fernando"

    private lateinit var playMerchant: PlayMerchant

    private val context = context()

    @Mock
    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        playMerchant = PlayMerchant(context, navigator)
    }

    @Test
    fun `should play movie trailer`() {
        val params = PlayMerchant.Params(VIDEO_URL)

        runBlocking { playMerchant(params) }

        verify(navigator).openVideo(context, VIDEO_URL)
    }
}