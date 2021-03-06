package com.example.imqq.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.ConversationListAdapter
import com.example.imqq.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_conversation

    val conversations = mutableListOf<EMConversation>()

    val messageLister = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversations()
        }
    }

        override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }
        EMClient.getInstance().chatManager().addMessageListener(messageLister)
        loadConversations()
    }

    private fun loadConversations() {

        doAsync {
            // 清空会话列表
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageLister)
    }
}