package com.example.imqq.ui.fragment

import com.example.imqq.R
import kotlinx.android.synthetic.main.header.*

class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
    }
}