package com.example.imqq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imqq.R
import com.example.imqq.adapter.EMMessageListenerAdapter
import com.example.imqq.factory.FragmentFactory
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread { updateBottomBarUnReadCount() }
        }
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)

    }

    override fun onResume() {
        super.onResume()
        updateBottomBarUnReadCount()
    }
    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
    private fun updateBottomBarUnReadCount() {
        //初始化bottombar未读消息的个数
        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }


}