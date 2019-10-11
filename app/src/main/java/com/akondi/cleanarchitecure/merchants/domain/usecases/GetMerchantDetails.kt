package com.akondi.cleanarchitecure.merchants.domain.usecases

import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetails
import com.akondi.cleanarchitecure.merchants.domain.usecases.GetMerchantDetails.Params
import com.akondi.cleanarchitecure.merchants.domain.repository.MerchantsRepository

import javax.inject.Inject

class GetMerchantDetails
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<MerchantDetails, Params>() {

    override suspend fun run(params: Params) = merchantsRepository.merchantDetails(params.id)

    data class Params(val id: Int)
}