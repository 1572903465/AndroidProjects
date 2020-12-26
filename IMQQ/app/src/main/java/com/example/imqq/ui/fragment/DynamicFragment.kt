package com.example.imqq.ui.fragment

import com.example.imqq.R
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*

class DynamicFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_dynamic
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)
        val logouString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)
        logout.text = logouString
    }
}