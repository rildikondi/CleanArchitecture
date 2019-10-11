package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

data class OpeningTimes(
    val standardOpeningTimes: StandardOpeningTimes
) {
    companion object {
        fun empty() =
            OpeningTimes(StandardOpeningTimes.empty())
    }
}