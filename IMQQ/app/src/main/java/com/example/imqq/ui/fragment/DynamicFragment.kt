package com.example.imqq.ui.fragment

import android.content.Intent
import com.example.imqq.R
import com.example.imqq.adapter.EMCallBackAdapter
import com.example.imqq.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class DynamicFragment:BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_dynamic
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)
        val logouString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)
        logout.text = logouString

        logout.setOnClickListener { logout() }
    }
    fun logout() {
        EMClient.getInstance().logout(true, object : EMCallBackAdapter() {

            override fun onSuccess() {
                context?.runOnUiThread {
                    toast(R.string.logout_success)
                    val intent = Intent( context,LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }

            }

            override fun onError(p0: Int, p1: String?) {
                // 切换到主线程
                context?.runOnUiThread {
                    toast(R.string.logout_failed)
                }
            }
        })
    }


}