package com.example.imqq.ui.activity

import com.example.imqq.R
import kotlinx.android.synthetic.main.header.*

class AddFriendActivity :BaseActivity(){
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
    }
}