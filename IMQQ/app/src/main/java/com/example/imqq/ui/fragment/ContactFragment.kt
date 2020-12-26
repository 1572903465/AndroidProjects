package com.example.imqq.ui.fragment

import android.view.View
import com.example.imqq.R
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            swipeRefreshLayout.isRefreshing = true
        }

    }
}