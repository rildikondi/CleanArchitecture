package com.akondi.cleanarchitecure.merchants.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.core.platform.BaseViewModel
import com.akondi.cleanarchitecure.merchants.domain.usecases.GetMerchants
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantView
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Merchants
import kotlinx.coroutines.launch
import javax.inject.Inject

class MerchantsViewModel
@Inject constructor(private val getMerchants: GetMerchants) : BaseViewModel() {

    var merchants: MutableLiveData<List<MerchantView>> = MutableLiveData()

    fun loadMerchants() = viewModelScope.launch {
        getMerchants(UseCase.None()) { it.either(::handleFailure, ::handleMerchantList) }
    }

    private fun handleMerchantList(merchants: Merchants) {
        this.merchants.value = merchants.merchants.map {
            MerchantView(
                it.id,
                it.name,
                it.images[0].url
            )
        }
    }
}