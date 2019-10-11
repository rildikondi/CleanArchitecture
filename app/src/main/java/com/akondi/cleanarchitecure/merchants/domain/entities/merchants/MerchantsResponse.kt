package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

data class MerchantsResponse(
    val limit: Int,
    val merchants: List<Merchant>,
    val offset: Int,
    val size: Int
) {
    companion object {
        fun empty() = MerchantsResponse(0, emptyList(), 0, 0)
    }
    fun toMerchants() = Merchants(limit, merchants, offset, size)
}