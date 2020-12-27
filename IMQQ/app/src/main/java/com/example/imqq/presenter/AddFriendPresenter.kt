package com.example.imqq.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.imqq.contract.AddFriendContract
import com.hyphenate.chat.EMClient

class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter{
    override fun search(key: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) view.onSearchSuccess()
                else view.onSearchFailed()
            }

        })
    }

}