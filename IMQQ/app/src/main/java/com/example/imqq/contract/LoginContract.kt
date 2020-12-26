package com.example.imqq.contract

interface LoginContract {
    interface Presenter:BasePresenter{
        fun login(userName: String,password: String)
    }

    interface Viwe {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInsuccess()
        fun onLoggedInfaild()
    }
}