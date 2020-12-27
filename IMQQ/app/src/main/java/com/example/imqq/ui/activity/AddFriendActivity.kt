package com.example.imqq.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imqq.R
import com.example.imqq.adapter.AddFriendListAdapter
import com.example.imqq.contract.AddFriendContract
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity :BaseActivity(),AddFriendContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }


    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_failed)
    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(R.string.search_success)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}