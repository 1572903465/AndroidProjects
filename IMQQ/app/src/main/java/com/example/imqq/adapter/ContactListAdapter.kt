package com.example.imqq.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imqq.R
import com.example.imqq.widget.ContactListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactListAdapter (val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int =30
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}