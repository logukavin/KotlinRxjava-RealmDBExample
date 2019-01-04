package com.example.orgware.kotlinrealmdb.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.orgware.kotlinrealmdb.R
import com.example.orgware.kotlinrealmdb.base.BaseActivity
import com.example.orgware.kotlinrealmdb.realmmodel.ContactList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity(), MainView {
    private var mainPresenter = MainPresenter()
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_main)
        mainPresenter.setView(this)
        mainPresenter.contact()


        rv_list.layoutManager = LinearLayoutManager(this)
        listAdapter = ListAdapter(this)
        rv_list.adapter = listAdapter
    }

    override fun onSuccess(contactList: ArrayList<ContactList>) {
        listAdapter!!.setFolderList(contactList)
    }


    override fun hideLoading() {

    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, "ErrorMessage :" + message, Toast.LENGTH_SHORT).show()
        Log.d("TAG", "Error:" + message)
    }

    override fun context(): Context {
        return this

    }

    override fun showLoading() {

    }
}