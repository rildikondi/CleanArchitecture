package com.akondi.cleanarchitecure.merchants.domain.entities.merchants


data class Location(
    val address: Address,
    val coordinates: Coordinates
) {
    companion object {
        fun empty() = Location(
            Address.empty(),
            Coordinates.empty()
        )
    }
}