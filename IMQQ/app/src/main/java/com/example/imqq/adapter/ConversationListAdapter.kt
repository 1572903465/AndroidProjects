package com.example.imqq.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imqq.ui.activity.ChatActivity
import com.example.imqq.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

class ConversationListAdapter(
    val context: Context,
    val conversations: MutableList<EMConversation>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
        conversationListItemView.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("username",conversations[position].conversationId())
            context.startActivity(intent)
        }
    }

    class ConversationListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    }
}