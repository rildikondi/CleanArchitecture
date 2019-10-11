package com.akondi.cleanarchitecure.merchants.domain.repository

import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.core.platform.NetworkHandler
import com.akondi.cleanarchitecure.merchants.data.MerchantsService
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetails
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetailsResponse
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Merchants
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantsResponse
import retrofit2.Call
import javax.inject.Inject

interface MerchantsRepository {
    fun merchants(): Either<Failure, Merchants>
    fun merchantDetails(merchantId: Int): Either<Failure, MerchantDetails>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: MerchantsService
    ) : MerchantsRepository {

        override fun merchants(): Either<Failure, Merchants> {
            return if (networkHandler.isConnected)
                request(
                    service.merchants(),
                    { it.toMerchants() },
                    MerchantsResponse.empty()
                )
            else Either.Left(Failure.NetworkConnection)
        }

        override fun merchantDetails(merchantId: Int): Either<Failure, MerchantDetails> {
            return if (networkHandler.isConnected)
                request(
                    service.merchantDetails(merchantId),
                    { it.toMerchantDetails() },
                    MerchantDetailsResponse.empty()
                )
            else
                Either.Left(Failure.NetworkConnection)
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}