package com.example.imqq.factory

import androidx.fragment.app.Fragment
import com.example.imqq.R
import com.example.imqq.ui.fragment.ContactFragment
import com.example.imqq.ui.fragment.ConversationFragment
import com.example.imqq.ui.fragment.DynamicFragment

class FragmentFactory private constructor(){

    val conversation by lazy {
        ConversationFragment()
    }

    val contact by lazy {
        ContactFragment()
    }

    val dynamic by lazy {
        DynamicFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId: Int): Fragment? {
        when(tabId)  {
            R.id.tab_conversation -> return conversation
            R.id.tab_contacts -> return contact
            R.id.tab_dynamic -> return dynamic
        }
        return null;
    }
}