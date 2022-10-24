package com.akondi.cleanarchitecure.characters.domain.repository

import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.core.platform.NetworkHandler
import com.akondi.cleanarchitecure.characters.data.MarvelService
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetails
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsResponse
import com.akondi.cleanarchitecure.characters.domain.entities.characters.Characters
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharactersResponse
import retrofit2.Call
import javax.inject.Inject

interface CharactersRepository {
    fun characters(): Either<Failure, Characters>
    fun characterDetails(characterId: Int): Either<Failure, CharacterDetails>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: MarvelService
    ) : CharactersRepository {

        override fun characters(): Either<Failure, Characters> {
            return if (networkHandler.isConnected)
                request(
                    service.characters(),
                    { it.toCharacters() },
                    CharactersResponse.empty()
                )
            else Either.Left(Failure.NetworkConnection)
        }

        override fun characterDetails(characterId: Int): Either<Failure, CharacterDetails> {
            return if (networkHandler.isConnected)
                request(
                    service.characterDetails(characterId = characterId),
                    { it.toCharacterDetails() },
                    CharacterDetailsResponse.empty()
                )
            else
                Either.Left(Failure.NetworkConnection)
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}