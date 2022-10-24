package com.akondi.cleanarchitecure.characters.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsView
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.extensions.*
import com.akondi.cleanarchitecure.core.platform.BaseFragment
import com.akondi.cleanarchitecure.characters.domain.entities.failures.CharactersFailure
import com.akondi.cleanarchitecure.characters.presentation.adapters.CharactersDetailsComicsAdapter
import com.akondi.cleanarchitecure.characters.presentation.animator.CharacterDetailsAnimator
import com.akondi.cleanarchitecure.characters.presentation.viewmodels.CharacterDetailsViewModel
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.slider.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class CharacterDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_CHARACTER = "param_character"

        fun forMerchant(characterView: CharacterView?): CharacterDetailsFragment {
            val characterDetailsFragment = CharacterDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_CHARACTER, characterView)
            characterDetailsFragment.arguments = arguments

            return characterDetailsFragment
        }
    }

    @Inject
    lateinit var characterDetailsAnimator: CharacterDetailsAnimator

    private lateinit var merchantDetailsViewModel: CharacterDetailsViewModel

    @Inject
    lateinit var comicsAdapter: CharactersDetailsComicsAdapter

    override fun layoutId() = R.layout.fragment_character_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { characterDetailsAnimator.postponeEnterTransition(it) }

        merchantDetailsViewModel = viewModel(viewModelFactory) {
            observe(characterDetails, ::renderCharacterDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comicsList.visible()
        comicsList.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        comicsList.adapter = comicsAdapter
        merchantDetailsViewModel.loadCharacterDetails((arguments?.get(PARAM_CHARACTER) as CharacterView).id)
    }


    override fun onBackPressed() {
        characterDetailsAnimator.fadeInvisible(scrollView, merchantDetails)
        if (characterPlay.isVisible())
            characterDetailsAnimator.scaleDownView(characterPlay)
        else
            characterDetailsAnimator.cancelTransition(characterPoster)
    }

    private fun renderCharacterDetails(character: CharacterDetailsView?) {
        character?.let {
            with(character) {
                activity?.let {
                    it.toolbar.title = name
                }
                characterDescription.text = character.description
                characterMainImage.loadFromUrl(character.thumbnail.path + "." +  character.thumbnail.extension)

            }
        }
        comicsAdapter.collection = character!!.comics.items
        characterDetailsAnimator.fadeVisible(scrollView, merchantDetails)
        characterDetailsAnimator.cancelTransition(characterPoster)
        characterDetailsAnimator.scaleUpView(characterPlay)

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is CharactersFailure.NonExistentCharacter -> {
                notify(R.string.failure_character_non_existent); close()
            }
        }
    }
}