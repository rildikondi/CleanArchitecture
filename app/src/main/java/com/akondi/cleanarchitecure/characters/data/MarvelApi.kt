package com.akondi.cleanarchitecure.characters.data

import com.akondi.cleanarchitecure.BuildConfig
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsResponse
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MarvelApi {
    companion object {
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
        private const val TIME_STAMP = "ts"
        private const val TIME_STAMP_VALUE = "1"
        private const val CHARACTER_ID = "characterId"
        private const val CHARACTERS = "characters"
        private const val CHARACTER_DETAILS = "characters/{$CHARACTER_ID}"
    }

    @GET(CHARACTERS)
    fun characters(
        @Query(TIME_STAMP) timeStamp: String = TIME_STAMP_VALUE,
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(HASH) hash: String = BuildConfig.HASH_VALUE
    ): Call<CharactersResponse>

    @GET(CHARACTER_DETAILS)
    fun characterDetails(
        @Path(CHARACTER_ID) characterId: Int,
        @Query(TIME_STAMP) timeStamp: String = TIME_STAMP_VALUE,
        @Query(API_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(HASH) hash: String = BuildConfig.HASH_VALUE
    ): Call<CharacterDetailsResponse>
}