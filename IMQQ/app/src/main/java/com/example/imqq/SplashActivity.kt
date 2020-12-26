package com.example.imqq

import android.content.Intent
import android.os.Handler
import com.example.imqq.contract.SplashContract


class SplashActivity :Baseactivity(),SplashContract.View{
    companion object{
        val DELAY = 2000L
    }
    val handler by lazy {
        Handler()
    }

    override fun getLayoutResId(): Int =
        R.layout.activity_splash

    // 未登录延时2s，跳转到登录界面
    override fun onNotLoggedIn() {
        handler.postDelayed({ //  val intent = Intent<LoginActivity>()
            val intent = Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY)
    }

        //跳转到主界面
    override fun onLoggedIn() {
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
    }
}