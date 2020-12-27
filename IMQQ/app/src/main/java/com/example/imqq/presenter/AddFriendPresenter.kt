package com.example.imqq.presenter

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.imqq.contract.AddFriendContract
import com.example.imqq.data.AddFriendItem
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.doAsync

class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter{
    val addFrienditems = mutableListOf<AddFriendItem>()
    override fun search(key: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                Log.d("BMob", p1.toString())
                if (p1 == null) {
                    // 处理数据 常见AddFrienditem集合
                    doAsync {
                        p0?.forEach {
                            val addFriendItem = AddFriendItem(it.username,it.createdAt,false)
                            addFrienditems.add(addFriendItem)
                        }
                    }
                    Log.d("BMob", addFrienditems.size.toString())
                    uiThread { view.onSearchSuccess() }
                }
                else view.onSearchFailed()
            }

        })
    }

}