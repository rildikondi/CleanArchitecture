package com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails

data class OpeningTimes(
    val standardOpeningTimes: StandardOpeningTimes
) {
    companion object {
        fun empty() =
            OpeningTimes(StandardOpeningTimes.empty())
    }
}