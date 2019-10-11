package com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails

data class Link(
    val href: String,
    val method: String,
    val rel: String
) {
    companion object {
        fun empty() = Location(
            Address.empty(),
            Coordinates.empty()
        )
    }
}