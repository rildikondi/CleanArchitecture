package com.akondi.cleanarchitecure.merchants.presentation.activities

import android.content.Context
import android.content.Intent
import com.akondi.cleanarchitecure.core.platform.BaseActivity
import com.akondi.cleanarchitecure.core.platform.BaseFragment
import com.akondi.cleanarchitecure.merchants.presentation.fragments.MerchantsFragment

class MerchantsActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MerchantsActivity::class.java)
    }

    override fun fragment(): BaseFragment = MerchantsFragment()
}
