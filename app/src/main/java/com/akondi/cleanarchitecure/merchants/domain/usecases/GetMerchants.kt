package com.akondi.cleanarchitecure.merchants.domain.usecases

import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.merchants.domain.repository.MerchantsRepository
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Merchants
import javax.inject.Inject

class GetMerchants
@Inject constructor(private val merchantsRepository: MerchantsRepository) : UseCase<Merchants, UseCase.None>() {

    override suspend fun run(params: None) = merchantsRepository.merchants()
}