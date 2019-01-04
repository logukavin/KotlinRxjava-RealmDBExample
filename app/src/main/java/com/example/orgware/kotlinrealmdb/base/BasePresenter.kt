package com.example.orgware.kotlinrealmdb.base


interface BasePresenter<in V : BaseView> {
    fun setView(view: V)

    fun destroyView()

    fun destroy()
}