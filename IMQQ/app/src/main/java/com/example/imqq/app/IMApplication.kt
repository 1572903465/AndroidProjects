package com.example.imqq.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.example.imqq.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

class IMApplication: Application() {
    companion object{
        lateinit var  instance: IMApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
        Bmob.initialize(applicationContext, "1896051197272c3c95fa08775428a9d9")

//        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }
}