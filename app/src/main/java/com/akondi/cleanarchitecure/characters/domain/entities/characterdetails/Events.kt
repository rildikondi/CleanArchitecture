package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)