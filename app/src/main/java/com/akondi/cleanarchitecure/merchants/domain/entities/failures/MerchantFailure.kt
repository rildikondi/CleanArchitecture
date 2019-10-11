package com.akondi.cleanarchitecure.merchants.domain.entities.failures

import com.akondi.cleanarchitecure.core.exception.Failure

class MerchantFailure {
    class MerchantsNotAvailable: Failure.FeatureFailure()
    class NonExistentMerchant: Failure.FeatureFailure()
}