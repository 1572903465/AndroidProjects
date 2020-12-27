package com.example.imqq.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.ConversationListAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_conversation

    val conversations = mutableListOf<EMConversation>()
        override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }

        loadConversations()
    }

    private fun loadConversations() {

        doAsync {
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }
}