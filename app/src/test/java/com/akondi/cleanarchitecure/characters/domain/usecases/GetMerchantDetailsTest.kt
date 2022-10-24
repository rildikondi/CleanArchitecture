package com.akondi.cleanarchitecure.characters.domain.usecases

import com.akondi.cleanarchitecure.UnitTest
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.characters.domain.entities.merchantdetails.MerchantDetails
import com.akondi.cleanarchitecure.characters.domain.repository.MerchantsRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMerchantDetailsTest : UnitTest() {

    companion object {
        private const val MERCHANT_ID = 1
    }

    private lateinit var getMerchantDetails: GetMerchantDetails

    @Mock
    private lateinit var merchantsRepository: MerchantsRepository

    @Before
    fun setUp() {
        getMerchantDetails = GetMerchantDetails(merchantsRepository)
        given { merchantsRepository.merchantDetails(MERCHANT_ID) }.willReturn(
            Either.Right(
                MerchantDetails.empty()
            )
        )
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMerchantDetails.run(GetMerchantDetails.Params(MERCHANT_ID)) }

        verify(merchantsRepository).merchantDetails(MERCHANT_ID)
        verifyNoMoreInteractions(merchantsRepository)
    }
}