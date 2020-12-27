package com.example.imqq.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import cn.bmob.v3.Bmob
import cn.bmob.v3.util.BmobNotificationManager.showNotification
import com.example.imqq.BuildConfig
import com.example.imqq.R
import com.example.imqq.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions

class IMApplication: Application() {
    companion object{
        lateinit var  instance: IMApplication
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy {
        soundPool.load(instance, R.raw.duan, 0)
    }

    val yulu by lazy {
        soundPool.load(instance, R.raw.yulu, 0)
    }

    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果是在前台，则播放短的声音
            if (isForeground()){
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                //如果在后台，则播放长的声音
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
            }
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
        Bmob.initialize(applicationContext, "1896051197272c3c95fa08775428a9d9")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun isForeground(): Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName){
                //找到了app的进程
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }
}