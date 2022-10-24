package com.akondi.cleanarchitecure.characters.domain.usecases

import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetails
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.characters.domain.usecases.GetCharacterDetails.Params
import com.akondi.cleanarchitecure.characters.domain.repository.CharactersRepository

import javax.inject.Inject

class GetCharacterDetails
@Inject constructor(private val charactersRepository: CharactersRepository) : UseCase<CharacterDetails, Params>() {

    override suspend fun run(params: Params) = charactersRepository.characterDetails(params.id)

    data class Params(val id: Int)
}