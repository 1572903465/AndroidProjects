package com.example.imqq.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imqq.data.AddFriendItem
import com.example.imqq.widget.AddFriendListItemView



class AddFriendListAdapter(
    val context: Context,
    val addFrienditems: MutableList<AddFriendItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = addFrienditems.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendListItemView = holder?.itemView as AddFriendListItemView
//        if(addFrienditems != null && addFrienditems.size!=0){
//            addFriendListItemView.bindView(addFrienditems[position])
//        }
        addFriendListItemView.bindView(addFrienditems[position])
    }
    class AddFriendListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}