package com.akondi.cleanarchitecure.merchants.domain.entities.merchantdetails

import  com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Image

data class MerchantDetailsView(
    val bookable: Boolean,
    val id: Int,
    val images: List<Image>,
    val location: Location,
    val name: String,
    val phoneNumber: String,
    val reviewScore: String
)