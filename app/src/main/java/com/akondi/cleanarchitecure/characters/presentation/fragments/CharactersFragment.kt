package com.akondi.cleanarchitecure.characters.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.extensions.invisible
import com.akondi.cleanarchitecure.core.extensions.observe
import com.akondi.cleanarchitecure.core.extensions.failure
import com.akondi.cleanarchitecure.core.extensions.viewModel
import com.akondi.cleanarchitecure.core.extensions.visible
import com.akondi.cleanarchitecure.core.navigation.Navigator
import com.akondi.cleanarchitecure.core.platform.BaseFragment
import com.akondi.cleanarchitecure.characters.domain.entities.failures.CharactersFailure
import com.akondi.cleanarchitecure.characters.presentation.viewmodels.CharactersViewModel
import com.akondi.cleanarchitecure.characters.presentation.adapters.CharactersAdapter
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    private lateinit var charactersViewModel: CharactersViewModel

    override fun layoutId() = R.layout.fragment_characters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        charactersViewModel = viewModel(viewModelFactory) {
            observe(characters, ::renderMerchants)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadCharacters()
    }

    private fun initializeView() {
        characterList.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        characterList.adapter = charactersAdapter
        charactersAdapter.clickListener = { merchant, navigationExtras ->
            navigator.showMerchantDetails(activity!!, merchant, navigationExtras)
        }
    }

    private fun loadCharacters() {
        emptyView.invisible()
        characterList.visible()
        showProgress()
        charactersViewModel.loadCharacters()
    }

    private fun renderMerchants(merchants: List<CharacterView>?) {
        charactersAdapter.collection = merchants.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is CharactersFailure.CharactersNotAvailable -> renderFailure(R.string.failure_characters_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        characterList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadCharacters)
    }
}