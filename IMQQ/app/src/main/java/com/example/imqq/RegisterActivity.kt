package com.example.imqq

import android.widget.Toast
import com.example.imqq.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity :BaseActivity(), RegisterContract.View{
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
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        Toast.makeText(this, R.string.register_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onUserExist() {
        TODO("Not yet implemented")
    }

}