package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)