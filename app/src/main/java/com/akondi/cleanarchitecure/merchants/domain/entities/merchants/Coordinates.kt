package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

data class Coordinates(
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun empty() = Coordinates(0.0, 0.0)
    }
}