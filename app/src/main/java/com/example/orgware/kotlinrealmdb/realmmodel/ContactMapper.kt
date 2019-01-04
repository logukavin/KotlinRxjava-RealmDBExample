package com.example.orgware.kotlinrealmdb.realmmodel

import com.example.orgware.kotlinrealmdb.model.ContactsItem
import io.realm.RealmResults

object ContactMapper {
    fun getModelFromPOJO(item: ContactsItem): RealmContactList {
        val realmContactList = RealmContactList()
        realmContactList.name = item.name!!
        realmContactList.phone=item.phone!!.mobile!!
        return realmContactList
    }
    fun convertModelToPOJO(dbList: RealmResults<RealmContactList>?): List<ContactList>? {
        val contactList = ArrayList<ContactList>()
        dbList!!.forEach {
            val normalList = ContactList()
            normalList.name = it.name!!
            normalList.phone = it.phone!!
            contactList.add(normalList)

        }
        return contactList
    }
}