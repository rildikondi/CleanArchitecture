package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)