package com.example.imqq.contract

interface BasePresenter {
    interface Presenter: BasePresenter {
        fun checkLoginStatus() //检查登录状态
    }

    interface View{
        fun onNotLoggedIn() //没有登陆的ui处理
        fun onLoggedIn()  //已经登录的ui处理
    }
}