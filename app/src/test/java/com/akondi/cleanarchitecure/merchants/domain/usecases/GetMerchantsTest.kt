package com.akondi.cleanarchitecure.merchants.domain.usecases

import com.akondi.cleanarchitecure.UnitTest
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Merchants
import com.akondi.cleanarchitecure.merchants.domain.repository.MerchantsRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMerchantsTest : UnitTest() {

    private lateinit var getMerchants: GetMerchants

    @Mock
    private lateinit var merchantsRepository: MerchantsRepository

    @Before
    fun setUp() {
        getMerchants = GetMerchants(merchantsRepository)
        given { merchantsRepository.merchants() }.willReturn(Either.Right(Merchants.empty()))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMerchants.run(UseCase.None()) }

        verify(merchantsRepository).merchants()
        verifyNoMoreInteractions(merchantsRepository)
    }
}