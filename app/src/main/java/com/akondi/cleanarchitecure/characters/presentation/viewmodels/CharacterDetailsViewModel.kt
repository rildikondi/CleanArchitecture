package com.akondi.cleanarchitecure.characters.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetails
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsView
import com.akondi.cleanarchitecure.core.platform.BaseViewModel
import com.akondi.cleanarchitecure.characters.domain.usecases.GetCharacterDetails
import com.akondi.cleanarchitecure.characters.domain.usecases.GetCharacterDetails.Params
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel
@Inject constructor(
    private val getCharacterDetails: GetCharacterDetails,
) : BaseViewModel() {

    var characterDetails: MutableLiveData<CharacterDetailsView> = MutableLiveData()

    fun loadCharacterDetails(characterId: Int) = viewModelScope.launch {
        getCharacterDetails(Params(characterId)) {
            it.either(
                ::handleFailure,
                ::handleCharacterDetails
            )
        }
    }

    private fun handleCharacterDetails(characterDetails: CharacterDetails) {
        this.characterDetails.value =
            CharacterDetailsView(
                characterDetails.comics,
                characterDetails.description,
                characterDetails.id,
                characterDetails.name,
                characterDetails.resourceURI,
                characterDetails.thumbnail,
                characterDetails.urls
            )
    }
}