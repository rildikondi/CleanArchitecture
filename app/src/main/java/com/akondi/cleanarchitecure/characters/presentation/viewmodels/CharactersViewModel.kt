package com.akondi.cleanarchitecure.characters.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.characters.domain.entities.characters.Characters
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.core.platform.BaseViewModel
import com.akondi.cleanarchitecure.characters.domain.usecases.GetCharacters
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel
@Inject constructor(private val getMerchants: GetCharacters) : BaseViewModel() {

    var characters: MutableLiveData<List<CharacterView>> = MutableLiveData()

    fun loadCharacters() = viewModelScope.launch {
        getMerchants(UseCase.None()) { it.either(::handleFailure, ::handleCharacterList) }
    }

    private fun handleCharacterList(characters: Characters) {
        this.characters.value = characters.results.map {
            CharacterView(
                it.id,
                it.name,
                it.thumbnail.path + "." +it.thumbnail.extension
            )
        }
    }
}