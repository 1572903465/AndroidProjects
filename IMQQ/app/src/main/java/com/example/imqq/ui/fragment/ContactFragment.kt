package com.example.imqq.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.ContactListAdapter
import com.example.imqq.contract.ContactContract
import com.example.imqq.presenter.ContactPresenter
import com.example.imqq.ui.activity.AddFriendActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment:BaseFragment(),ContactContract.View{
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener{
            val intent = Intent(context,AddFriendActivity::class.java)
            startActivity(intent)
        }

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            swipeRefreshLayout.isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            Log.d("contactListItem",presenter.contactListItems.size.toString())
            adapter = ContactListAdapter(context,presenter.contactListItems)
        }

        presenter.loadContacts()

    }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactsFailed() {
        Toast.makeText(context, R.string.load_contacts_failed, Toast.LENGTH_SHORT).show()
    }
}