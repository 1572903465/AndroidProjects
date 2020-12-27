package com.example.imqq.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.imqq.R
import com.example.imqq.data.ContactListItem

import kotlinx.android.synthetic.main.view_contact_item.view.*


class ContactListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(contactListItem: ContactListItem) {
        firstLetter.text  = contactListItem.firstLetter.toString()
        userName.text = contactListItem.userName
    }

    init {
        View.inflate(context, R.layout.view_contact_item, this)
    }



}