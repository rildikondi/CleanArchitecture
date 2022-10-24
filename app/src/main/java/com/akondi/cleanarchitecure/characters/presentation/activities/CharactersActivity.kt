package com.akondi.cleanarchitecure.characters.presentation.activities

import android.content.Context
import android.content.Intent
import com.akondi.cleanarchitecure.core.platform.BaseActivity
import com.akondi.cleanarchitecure.core.platform.BaseFragment
import com.akondi.cleanarchitecure.characters.presentation.fragments.CharactersFragment

class CharactersActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, CharactersActivity::class.java)
    }

    override fun fragment(): BaseFragment = CharactersFragment()
}
