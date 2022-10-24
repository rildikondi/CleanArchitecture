package com.akondi.cleanarchitecure.characters.data

import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsResponse
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharactersResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelService
@Inject constructor(retrofit: Retrofit) :
    MarvelApi {
    private val marvelApi by lazy { retrofit.create(MarvelApi::class.java) }

    override fun characters(
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Call<CharactersResponse> = marvelApi.characters()

    override fun characterDetails(
        characterId: Int,
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Call<CharacterDetailsResponse> = marvelApi.characterDetails(characterId = characterId)
}