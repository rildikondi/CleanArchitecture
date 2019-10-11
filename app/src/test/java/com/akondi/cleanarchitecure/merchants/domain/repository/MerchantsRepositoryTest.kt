package com.akondi.cleanarchitecure.merchants.domain.repository

import com.akondi.cleanarchitecure.UnitTest
import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.extensions.empty
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.core.platform.NetworkHandler
import com.akondi.cleanarchitecure.merchants.data.MerchantsService
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.Location
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetails
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetailsResponse
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.OpeningTimes
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Merchants
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantsResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class MerchantsRepositoryTest : UnitTest() {

    private lateinit var networkRepository: MerchantsRepository.Network

    @Mock
    private lateinit var networkHandler: NetworkHandler
    @Mock
    private lateinit var service: MerchantsService

    @Mock
    private lateinit var merchantsCall: Call<MerchantsResponse>
    @Mock
    private lateinit var merchantsResponse: Response<MerchantsResponse>
    @Mock
    private lateinit var merchantDetailsCall: Call<MerchantDetailsResponse>
    @Mock
    private lateinit var merchantDetailsResponse: Response<MerchantDetailsResponse>

    @Before
    fun setUp() {
        networkRepository = MerchantsRepository.Network(networkHandler, service)
    }

    @Test
    fun `should return empty merchants by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { merchantsResponse.body() }.willReturn(null)
        given { merchantsResponse.isSuccessful }.willReturn(true)
        given { merchantsCall.execute() }.willReturn(merchantsResponse)
        given { service.merchants() }.willReturn(merchantsCall)

        val merchants = networkRepository.merchants()

        merchants shouldEqual Either.Right(Merchants.empty())
        verify(service).merchants()
    }

    @Test
    fun `should get merchants from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { merchantsResponse.body() }.willReturn(MerchantsResponse(100, emptyList(), 300, 100))
        given { merchantsResponse.isSuccessful }.willReturn(true)
        given { merchantsCall.execute() }.willReturn(merchantsResponse)
        given { service.merchants() }.willReturn(merchantsCall)

        val merchants = networkRepository.merchants()

        merchants shouldEqual Either.Right(Merchants(100, emptyList(), 300, 100))
        verify(service).merchants()
    }

    @Test
    fun `merchants service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val merchants = networkRepository.merchants()

        merchants shouldBeInstanceOf Either::class.java
        merchants.isLeft shouldEqual true
        merchants.either(
            { failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java },
            {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `merchants service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val merchants = networkRepository.merchants()

        merchants shouldBeInstanceOf Either::class.java
        merchants.isLeft shouldEqual true
        merchants.either(
            { failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java },
            {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `merchant service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val merchants = networkRepository.merchants()

        merchants shouldBeInstanceOf Either::class.java
        merchants.isLeft shouldEqual true
        merchants.either(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {})
    }

    @Test
    fun `merchants request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val merchants = networkRepository.merchants()

        merchants shouldBeInstanceOf Either::class.java
        merchants.isLeft shouldEqual true
        merchants.either(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {})
    }

    @Test
    fun `should return empty merchant details by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { merchantDetailsResponse.body() }.willReturn(null)
        given { merchantDetailsResponse.isSuccessful }.willReturn(true)
        given { merchantDetailsCall.execute() }.willReturn(merchantDetailsResponse)
        given { service.merchantDetails(1) }.willReturn(merchantDetailsCall)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldEqual Either.Right(MerchantDetails.empty())
        verify(service).merchantDetails(1)
    }

    @Test
    fun `should get merchant details from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { merchantDetailsResponse.body() }.willReturn(
            MerchantDetailsResponse(
                bookable = true,
                ccvEnabled = false,
                currency = "euro",
                documents = emptyList(),
                id = 1234,
                images = emptyList(),
                links = emptyList(),
                locale = "eu",
                location = Location.empty(),
                name = "Example Restaurant",
                openingTimes = OpeningTimes.empty(),
                phoneNumber = String.empty(),
                reviewScore = String.empty(),
                tagGroups = emptyList(),
                timezone = String.empty()
            )
        )
        given { merchantDetailsResponse.isSuccessful }.willReturn(true)
        given { merchantDetailsCall.execute() }.willReturn(merchantDetailsResponse)
        given { service.merchantDetails(1) }.willReturn(merchantDetailsCall)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldEqual Either.Right(
            MerchantDetails(
                bookable = true,
                ccvEnabled = false,
                currency = "euro",
                documents = emptyList(),
                id = 1234,
                images = emptyList(),
                links = emptyList(),
                locale = "eu",
                location = Location.empty(),
                name = "Example Restaurant",
                openingTimes = OpeningTimes.empty(),
                phoneNumber = String.empty(),
                reviewScore = String.empty(),
                tagGroups = emptyList(),
                timezone = String.empty()
            )
        )
        verify(service).merchantDetails(1)
    }

    @Test
    fun `merchant details service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldBeInstanceOf Either::class.java
        merchantDetails.isLeft shouldEqual true
        merchantDetails.either(
            { failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java },
            {}
        )
        verifyZeroInteractions(service)
    }

    @Test
    fun `merchant details service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldBeInstanceOf Either::class.java
        merchantDetails.isLeft shouldEqual true
        merchantDetails.either(
            { failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java },
            {}
        )
        verifyZeroInteractions(service)
    }

    @Test
    fun `merchant details service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldBeInstanceOf Either::class.java
        merchantDetails.isLeft shouldEqual true
        merchantDetails.either(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {}
        )
    }

    @Test
    fun `merchant details request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val merchantDetails = networkRepository.merchantDetails(1)

        merchantDetails shouldBeInstanceOf Either::class.java
        merchantDetails.isLeft shouldEqual true
        merchantDetails.either(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {}
        )
    }
}