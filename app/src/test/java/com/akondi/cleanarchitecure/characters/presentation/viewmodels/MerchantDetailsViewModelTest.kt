package com.akondi.cleanarchitecure.characters.presentation.viewmodels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akondi.cleanarchitecure.AndroidTest
import com.akondi.cleanarchitecure.CoroutinesTestRule
import com.akondi.cleanarchitecure.core.extensions.empty
import com.akondi.cleanarchitecure.characters.domain.entities.merchantdetails.Location
import com.akondi.cleanarchitecure.characters.domain.entities.merchantdetails.MerchantDetails
import com.akondi.cleanarchitecure.characters.domain.entities.merchantdetails.OpeningTimes
import com.akondi.cleanarchitecure.characters.domain.usecases.GetMerchantDetails
import com.akondi.cleanarchitecure.characters.domain.usecases.PlayMerchant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

class MerchantDetailsViewModelTest : AndroidTest() {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        private const val MERCHANT_ID = 1
    }

    private lateinit var merchantDetailsViewModel: MerchantDetailsViewModel

    @Mock
    private lateinit var getMerchantDetails: GetMerchantDetails
    @Mock
    private lateinit var playMerchant: PlayMerchant

    val merchantDetails = MerchantDetails(
        true,
        false,
        "euro",
        emptyList(),
        MERCHANT_ID,
        emptyList(),
        emptyList(),
        "Berlin",
        Location.empty(),
        String.empty(),
        OpeningTimes.empty(),
        String.empty(),
        String.empty(),
        emptyList(),
        String.empty()
    )

    @Before
    fun setUp() {
        merchantDetailsViewModel = MerchantDetailsViewModel(getMerchantDetails, playMerchant)
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `loading merchant details should update live data`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            getMerchantDetails.run(GetMerchantDetails.Params(MERCHANT_ID))

            verify(getMerchantDetails).run(GetMerchantDetails.Params(MERCHANT_ID))

        }
}
