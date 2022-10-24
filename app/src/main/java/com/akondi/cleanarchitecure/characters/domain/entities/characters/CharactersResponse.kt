package com.akondi.cleanarchitecure.characters.domain.entities.characters

import com.akondi.cleanarchitecure.core.extensions.empty

data class CharactersResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
) {
    companion object {
        fun empty() = CharactersResponse(
            String.empty(),
            String.empty(),
            0,
            String.empty(),
            Data.empty(),
            String.empty(),
            String.empty()
        )
    }

    fun toCharacters() = Characters(`data`.results)
}