package com.example.imqq

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.imqq.contract.LoginContract
import com.example.imqq.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        newUser.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            login()
        }
        password.setOnEditorActionListener { p0, p1, p2 ->
            login()
            true
        }
    }
    fun login(){
        // 软键盘
        hideSoftKeyboard()
        if(hasWriteExternalStoragePermisssion()){
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString,passwordString)
        }else applyWriteExteranlStoragePermissino()

    }

    private fun applyWriteExteranlStoragePermissino() {
        val permsions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permsions,0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 用户统一2权限，开始登录
            login()
        }else Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
    }

    // 检查是有写磁盘的权限
    private fun hasWriteExternalStoragePermisssion(): Boolean {
        val result=ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

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