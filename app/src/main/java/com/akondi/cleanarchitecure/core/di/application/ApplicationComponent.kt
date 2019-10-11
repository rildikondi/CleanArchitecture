package com.akondi.cleanarchitecure.core.di.application

import com.akondi.cleanarchitecure.application.AndroidApplication
import com.akondi.cleanarchitecure.core.di.viewmodel.ViewModelModule
import com.akondi.cleanarchitecure.core.navigation.RouteActivity
import com.akondi.cleanarchitecure.merchants.presentation.fragments.MerchantDetailsFragment
import com.akondi.cleanarchitecure.merchants.presentation.fragments.MerchantsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(merchantsFragment: MerchantsFragment)
    fun inject(merchantDetailsFragment: MerchantDetailsFragment)
}