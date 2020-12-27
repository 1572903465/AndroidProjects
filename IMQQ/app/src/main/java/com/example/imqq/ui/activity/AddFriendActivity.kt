package com.example.imqq.ui.activity

import android.view.KeyEvent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.AddFriendListAdapter
import com.example.imqq.contract.AddFriendContract
import com.example.imqq.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity :BaseActivity(),AddFriendContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_add_friend
    val presenter = AddFriendPresenter(this)

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
        search.setOnClickListener { serach() }
        userName.setOnEditorActionListener { p0, p1, p2 ->
            serach()
            true
        }
    }
    fun serach(){
        hideSoftKeyboard()
        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_success)
    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(R.string.search_failed)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}