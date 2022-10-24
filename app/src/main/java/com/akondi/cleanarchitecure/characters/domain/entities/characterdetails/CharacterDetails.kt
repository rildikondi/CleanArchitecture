package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails

class CharacterDetails (
    val comics: Comics,
    val description: String,
    val id: Int,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)