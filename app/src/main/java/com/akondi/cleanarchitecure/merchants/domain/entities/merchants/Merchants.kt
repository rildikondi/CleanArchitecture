package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

data class Merchants(
    val limit: Int,
    val merchants: List<Merchant>,
    val offset: Int,
    val size: Int
) {
    companion object {
        fun empty() = Merchants(0, emptyList(), 0, 0)
    }
}