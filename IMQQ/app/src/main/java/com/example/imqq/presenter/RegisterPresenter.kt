package com.example.imqq.presenter

import com.example.imqq.contract.RegisterContract
import com.example.imqq.extentions.isValidUserName

class RegisterPresenter(val view: RegisterContract.View): RegisterContract.Presenter{
    override fun register(userName: String, password: String, confirmPassword: String) {
        if(userName.isValidUserName()){
            // 检查密码
            if(password.isValidUserName()){
                // 检查确认密码
                if (password.equals(confirmPassword)){
                    // 密码和确认密码一致
                    view.onStartRegister()
                    // 开始注册
                } else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }
}