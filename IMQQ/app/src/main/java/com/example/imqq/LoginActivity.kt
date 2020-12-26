package com.example.imqq

import android.content.Intent
import android.widget.Toast
import com.example.imqq.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.Viwe {
    override fun getLayoutResId(): Int = R.layout.activity_login
    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
       password.error=getString(R.string.password_error)
    }

    override fun onStartLogin() {
       // 弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInsuccess() {
        // 隐藏进度条
        dismissProgress()
        //进入主界面
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        // 退出LoginACtivity
        finish()
    }

    override fun onLoggedInfaild() {
        // 隐藏进度条
        dismissProgress()
        // 弹出toast
       Toast.makeText(this,getString(R.string.login_failed),Toast.LENGTH_SHORT).show()
    }
}