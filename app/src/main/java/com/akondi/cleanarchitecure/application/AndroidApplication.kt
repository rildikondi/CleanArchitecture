package com.akondi.cleanarchitecure.application

import android.app.Application
import com.akondi.cleanarchitecure.core.di.application.ApplicationComponent
import com.akondi.cleanarchitecure.core.di.application.ApplicationModule
import com.akondi.cleanarchitecure.core.di.application.DaggerApplicationComponent


class  AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}