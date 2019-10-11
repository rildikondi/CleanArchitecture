package com.akondi.cleanarchitecure.merchants.data

import com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails.MerchantDetailsResponse
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MerchantsApi {
    companion object {
        private const val HEADER_API_PARAM = "X-Quandoo-AuthToken"
        private const val HEADER_API_KEY = "e6f6h28e26vbc8442b288eb6121d85b9a4"
        private const val PARAM_MERCHANT_ID = "merchantId"
        private const val MERCHANTS = "merchants"
        private const val MERCHANT_DETAILS = "merchants/{$PARAM_MERCHANT_ID}"
    }

    @GET(MERCHANTS)
    fun merchants(/*@Header(HEADER_API_PARAM) token: String = HEADER_API_KEY/,*/
//        @Query("query") query: String = "",
//        @Query("place") place: String = "",
//        @Query("centerPoint") centerPoint: String = "",
//        @Query("radius") radius: String = "",
//        @Query("bookable") bookableOnline: Boolean = false,
//        @Query("tags") tags: String = "",
//        @Query("tagsIds") tagsIds: String = "",
//        @Query("reviewScoreMax") reviewScoreMax: String = "",
//        @Query("onlyWithAttributes") onlyWithAttributes: String = "",
//        @Query("capacity") capacity: String = "",
//        @Query("date") date: String = "",
//        @Query("fromTime") fromTime: String = "",
//        @Query("offset") offset: String = "0",
//        @Query("limit") limit: String = "100",
//        @Query("bookable") bookable: String = ""
    ): Call<MerchantsResponse>

    @GET("merchants/{merchantId}")
    fun merchantDetails(
        /*@Header(HEADER_API_PARAM) token: String = HEADER_API_KEY,*/
        @Path("merchantId") merchantId: Int
    ): Call<MerchantDetailsResponse>
}