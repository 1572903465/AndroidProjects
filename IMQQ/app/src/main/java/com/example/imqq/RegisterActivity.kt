package com.example.imqq

import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import com.example.imqq.contract.RegisterContract
import com.example.imqq.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity :BaseActivity(), RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener {
            register()
        }
        confirmPassword.setOnEditorActionListener { p0, p1, p2 ->
            register()
            true
        }
    }
     fun register(){
         // 隐藏软键盘
         hideSoftKeyboard()
         val userNameString = userName.text.trim().toString()
         val passwordString = password.text.trim().toString()
         val confirmPasswordString = confirmPassword.text.trim().toString()
         presenter.register(userNameString,passwordString,confirmPasswordString)
     }

    override fun getLayoutResId(): Int = R.layout.activity_register

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
       password.error=getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        password.error=getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        Toast.makeText(this, R.string.register_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onUserExist() {
        Toast.makeText(this, R.string.user_already_exist, Toast.LENGTH_SHORT).show()
    }

}