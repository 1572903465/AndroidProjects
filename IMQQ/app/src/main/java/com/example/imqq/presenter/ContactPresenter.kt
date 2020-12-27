package com.example.imqq.presenter


import com.example.imqq.contract.ContactContract
import com.example.imqq.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {
    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                // 根据首字符排序
                usernames.sortBy { it[0] }
                usernames.forEach{
                    val contactListItem = ContactListItem(it,it[0].toUpperCase())
                    contactListItems.add(contactListItem)
                }
                uiThread { view.onLoadContactsSuccess()  }
            }catch (e:HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }
        }
    }
}
