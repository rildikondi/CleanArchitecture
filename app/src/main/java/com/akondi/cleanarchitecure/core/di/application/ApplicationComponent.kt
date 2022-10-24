package com.akondi.cleanarchitecure.core.di.application

import com.akondi.cleanarchitecure.application.AndroidApplication
import com.akondi.cleanarchitecure.core.di.viewmodel.ViewModelModule
import com.akondi.cleanarchitecure.core.navigation.RouteActivity
import com.akondi.cleanarchitecure.characters.presentation.fragments.CharacterDetailsFragment
import com.akondi.cleanarchitecure.characters.presentation.fragments.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(merchantsFragment: CharactersFragment)
    fun inject(merchantDetailsFragment: CharacterDetailsFragment)
}