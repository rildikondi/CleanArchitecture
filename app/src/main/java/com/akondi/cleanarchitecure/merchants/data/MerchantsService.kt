package com.akondi.cleanarchitecure.merchants.data

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MerchantsService
@Inject constructor(retrofit: Retrofit) :
    MerchantsApi {
    private val merchantsApi by lazy { retrofit.create(MerchantsApi::class.java) }

    override fun merchants(
//        query: String,
//        place: String,
//        centerPoint: String,
//        radius: String,
//        bookableOnline: Boolean,
//        tags: String,
//        tagsIds: String,
//        reviewScoreMax: String,
//        onlyWithAttributes: String,
//        capacity: String,
//        date: String,
//        fromTime: String,
//        offset: String,
//        limit: String,
//        bookable: String
    ) = merchantsApi.merchants()

    override fun merchantDetails(merchantId: Int) = merchantsApi.merchantDetails(merchantId)
}