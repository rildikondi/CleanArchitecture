package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails
import com.akondi.cleanarchitecure.core.extensions.empty

data class CharacterDetailsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
) {
    companion object {
        fun empty() = CharacterDetailsResponse(
            String.empty(),
            String.empty(),
            0,
            String.empty(),
            Data.empty(),
            String.empty(),
            String.empty()
        )
    }

    fun toCharacterDetails() = CharacterDetails(
        `data`.results.first().comics,
        `data`.results.first().description,
        `data`.results.first().id,
        `data`.results.first().name,
        `data`.results.first().resourceURI,
        `data`.results.first().thumbnail,
        `data`.results.first().urls
    )
}