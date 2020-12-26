package com.example.imqq.presenter

import com.example.imqq.adapter.EMCallBackAdapter
import com.example.imqq.contract.LoginContract
import com.example.imqq.extentions.*
import com.hyphenate.chat.EMClient

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()) {
            //用户名合法，继续校验密码
            if (password.isValidPassword()) {
                //密码合法，开始登陆
                view.onStartLogin()
                loginEaseMob(userName, password)//登陆到环信
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName, password, object : EMCallBackAdapter() {
            //在子线程回调
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //在主线程通知View层
                uiThread { view.onLoggedInsuccess()}
            }
            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoggedInfaild() }
            }
        })
    }

}