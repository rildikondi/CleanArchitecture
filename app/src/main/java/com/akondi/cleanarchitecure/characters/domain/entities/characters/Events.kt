package com.akondi.cleanarchitecure.characters.domain.entities.characters

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)