package com.example.imqq.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.imqq.R
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*

class ChatActivity:BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEditTest()
    }

    private fun initEditTest() {
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // 如果用户输入的文本长度大于0，发送按钮enable
                send.isEnabled = !p0.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        val username = intent.getStringExtra("username")
        val titleString = String.format(getString(R.string.chat_title),username)
        headerTitle.text = titleString

    }
}