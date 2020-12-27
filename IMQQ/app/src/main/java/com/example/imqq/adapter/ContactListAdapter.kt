package com.example.imqq.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imqq.R
import com.example.imqq.data.ContactListItem
import com.example.imqq.ui.activity.ChatActivity
import com.example.imqq.widget.ContactListItemView
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

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
        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message),userName)
            AlertDialog.Builder(context)
                .setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener { dialogInterface, i ->
                    deleteFriend(userName)
                })
                .show()
                 true
        }
    }
    private fun  deleteFriend(userName: String){
        EMClient.getInstance().contactManager().aysncDeleteContact(userName,object : EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.delete_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.delete_friend_failed) }
            }

        })
    }
    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}