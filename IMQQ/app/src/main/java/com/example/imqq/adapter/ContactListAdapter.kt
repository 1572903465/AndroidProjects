package com.example.imqq.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imqq.data.ContactListItem
import com.example.imqq.ui.activity.ChatActivity
import com.example.imqq.widget.ContactListItemView

class ContactListAdapter(
    val context: Context,
   val contactListItems: MutableList<ContactListItem>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int = contactListItems.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        val userName = contactListItems[position].userName
        contactListItemView.bindView(contactListItems[position])
        contactListItemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("username",userName)
            context.startActivity(intent)
        }
    }
    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}