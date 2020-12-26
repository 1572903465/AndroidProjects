package com.example.imqq.presenter

import com.example.imqq.contract.LoginContract
import com.example.imqq.extentions.*

class LoginPresenter(val view: LoginContract.Viwe): LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        if (userName.lsValidUserName()){
            // 用户名合法,继续校验密码
            if (password.isValidPassword()) {
                // 密码合法，开始登录
                view.onStartLogin()
                loginEaseMob(userName,password) //登录到环信
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun loginEaseMob(userName: String, password: String) {

    }
}