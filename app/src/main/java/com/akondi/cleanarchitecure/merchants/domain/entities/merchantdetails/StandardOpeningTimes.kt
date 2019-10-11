package com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails

data class StandardOpeningTimes(
    val FRIDAY: List<FRIDAY>,
    val MONDAY: List<MONDAY>,
    val SATURDAY: List<SATURDAY>,
    val SUNDAY: List<SUNDAY>,
    val THURSDAY: List<THURSDAY>,
    val TUESDAY: List<TUESDAY>,
    val WEDNESDAY: List<WEDNESDAY>
) {
    companion object {
        fun empty() = StandardOpeningTimes(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
    }
}