package com.akondi.cleanarchitecure.core.navigation

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.login.LoginActivity
import com.akondi.cleanarchitecure.login.Authenticator
import com.akondi.cleanarchitecure.characters.presentation.activities.CharacterDetailsActivity
import com.akondi.cleanarchitecure.characters.presentation.activities.CharactersActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMerchants(context)
            false -> showLogin(context)
        }
    }

    private fun showMerchants(context: Context) = context.startActivity(CharactersActivity.callingIntent(context))

    fun showMerchantDetails(activity: FragmentActivity, characterView: CharacterView, navigationExtras: Extras) {
        val intent = CharacterDetailsActivity.callingIntent(activity, characterView)
        activity.startActivity(intent)
    }

    class Extras(val transitionSharedElement: View)
}