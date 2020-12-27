package com.example.imqq.presenter


import com.example.imqq.contract.ContactContract
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {
    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.onLoadContactsSuccess()  }
            }catch (e:HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }
        }
    }
}
