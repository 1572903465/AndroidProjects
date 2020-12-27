package com.example.imqq.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    }

    class ConversationListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    }
}