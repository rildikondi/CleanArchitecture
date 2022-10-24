package com.akondi.cleanarchitecure.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akondi.cleanarchitecure.characters.presentation.viewmodels.CharactersViewModel
import com.akondi.cleanarchitecure.characters.presentation.viewmodels.CharacterDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindsMoviesViewModel(merchantsViewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(merchantDetailsViewModel: CharacterDetailsViewModel): ViewModel
}