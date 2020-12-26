package com.example.imqq.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            swipeRefreshLayout.isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }

    }
}