package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

import com.akondi.cleanarchitecure.core.extensions.empty


data class Address(
    val country: String
) {
    companion object {
        fun empty() = Address(String.empty())
    }
}