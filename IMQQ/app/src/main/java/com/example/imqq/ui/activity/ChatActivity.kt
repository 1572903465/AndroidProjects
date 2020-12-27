package com.example.imqq.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.EMMessageListenerAdapter
import com.example.imqq.adapter.MessageListAdapter
import com.example.imqq.contract.ChatContract
import com.example.imqq.presenter.ChatPresenter
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity:BaseActivity(),ChatContract.View {
    override fun getLayoutResId(): Int = R.layout.activity_chat

    val presenter = ChatPresenter(this)
    lateinit var username: String

    val messageListener = object:EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread {
                recyclerView.adapter?.notifyDataSetChanged()
                scrollBottom()
            }
        }
    }
    override fun init() {
        super.init()
        initHeader()
        initEditTest()
        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        send.setOnClickListener { send() }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.messages)
        }
    }

    fun send(){
        hideSoftKeyboard()
        val message = edit.text.trim().toString()
        presenter.sendMessage(username,message)
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
        edit.setOnEditorActionListener { p0, p1, p2 ->
            send()
            true
        }
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        username = intent.getStringExtra("username").toString()
        val titleString = String.format(getString(R.string.chat_title),username)
        headerTitle.text = titleString

    }

    override fun onStartSendMessage() {
        // 通知RecycView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        // 通知RecycView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        // 清空编辑框
        edit.text.clear()
        scrollBottom()
    }

    private fun scrollBottom() {
        recyclerView.scrollToPosition(presenter.messages.size-1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        // 通知RecycView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onMessageLoaded() {

    }

    override fun onMoreMessageLoaded(size: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}