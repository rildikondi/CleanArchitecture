package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

data class Merchant(
    val bookable: Boolean,
    val ccvEnabled: Boolean,
    val currency: String,
    val documents: List<Any>,
    val id: Int,
    val images: List<Image>,
    val links: List<Link>,
    val locale: String,
    val location: Location,
    val name: String,
    val openingTimes: OpeningTimes,
    val phoneNumber: String,
    val reviewScore: String,
    val tagGroups: List<TagGroup>,
    val timezone: String
)