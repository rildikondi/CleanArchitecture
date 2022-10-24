package com.akondi.cleanarchitecure.characters.domain.usecases

import com.akondi.cleanarchitecure.characters.domain.entities.characters.Characters
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacters
@Inject constructor(private val charactersRepository: CharactersRepository) :
    UseCase<Characters, UseCase.None>() {

    override suspend fun run(params: None) = charactersRepository.characters()
}