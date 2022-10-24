package com.akondi.cleanarchitecure.characters.presentation.activities

import android.content.Context
import android.content.Intent
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.core.platform.BaseActivity
import com.akondi.cleanarchitecure.characters.presentation.fragments.CharacterDetailsFragment

class CharacterDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_CHARACTER = "com.akondi.cleanarchitecure.INTENT_PARAM_CHARACTER"

        fun callingIntent(context: Context, characterView: CharacterView): Intent {
            val intent = Intent(context, CharacterDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_CHARACTER, characterView)
            return intent
        }
    }

    override fun fragment() =
        CharacterDetailsFragment.forMerchant(
            intent.getParcelableExtra(INTENT_EXTRA_PARAM_CHARACTER)
        )
}