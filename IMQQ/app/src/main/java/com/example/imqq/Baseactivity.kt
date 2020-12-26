package com.example.imqq

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class Baseactivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    private fun init() {
        // 初始化一些公共的功能
    }

    //子类返回一个布局
    abstract fun getLayoutResId(): Int



}