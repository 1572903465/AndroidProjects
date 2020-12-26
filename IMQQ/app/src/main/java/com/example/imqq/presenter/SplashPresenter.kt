package com.example.imqq.presenter

import com.example.imqq.contract.SplashContract

class SplashPresenter(val view: SplashContract.View): SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }
    private fun isLoggedIn(): Boolean = false
}