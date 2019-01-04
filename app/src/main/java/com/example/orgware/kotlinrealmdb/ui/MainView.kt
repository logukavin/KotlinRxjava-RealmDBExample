package com.example.orgware.kotlinrealmdb.ui

import com.example.orgware.kotlinrealmdb.base.LoadDataView
import com.example.orgware.kotlinrealmdb.realmmodel.ContactList
import java.util.ArrayList

interface MainView: LoadDataView {
    fun onSuccess(contactList: ArrayList<ContactList>)
}