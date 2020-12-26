package com.example.imqq.contract


interface ContactContract{
    interface  Presenter: BasePresenter{
        fun loadContacts()
    }

    interface  View {
        fun onLoadContactsSuccess()
        fun onLoadContactsFailed()
    }
}