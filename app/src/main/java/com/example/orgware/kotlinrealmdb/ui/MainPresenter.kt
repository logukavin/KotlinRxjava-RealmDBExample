package com.example.orgware.kotlinrealmdb.ui

import com.example.orgware.kotlinrealmdb.app.AppController
import com.example.orgware.kotlinrealmdb.base.AbstractBasePresenter
import com.example.orgware.kotlinrealmdb.model.ContactResponse
import com.example.orgware.kotlinrealmdb.model.ContactsItem
import com.example.orgware.kotlinrealmdb.realmmodel.ContactList
import com.example.orgware.kotlinrealmdb.realmmodel.ContactMapper
import com.example.orgware.kotlinrealmdb.realmmodel.RealmContactList
import com.example.orgware.kotlinrealmdb.utils.RxJavaUtils
import io.realm.Realm
import java.util.ArrayList

class MainPresenter: AbstractBasePresenter<MainView>() {

    private var mainView: MainView? = null
    private val contactsItem = ArrayList<ContactsItem>()
    private var contactList = ArrayList<ContactList>()


    override fun setView(view: MainView) {
        mainView = view
        appRepo = AppController.getInstanse()!!.getappRepo()
    }

    fun contact() {
        appRepo!!.getContact()
                .compose(RxJavaUtils.applyObserverSchedulers())
            .subscribe({ contactResponse ->
                if (mainView != null) {
                    if (contactResponse != null)
                        saveResponseToDb(contactResponse as ContactResponse)
                    mainView!!.hideLoading()
                }
            }, { throwable ->
                if (mainView != null) {
                    getListFromDb()
                    mainView!!.showError(throwable.message!!)
                    mainView!!.hideLoading()
                }
            });

    }

    private fun saveResponseToDb(contactResponse: ContactResponse) {
        val realm = Realm.getInstance(AppController.getInstanse()!!.getRealmConfiguration())
        try {
            realm.beginTransaction()
            realm.where(RealmContactList::class.java).findAll().deleteAllFromRealm()
            realm.commitTransaction()
            contactsItem.clear()
            for (i in contactResponse.contacts!!.indices) {
                contactsItem.add(contactResponse.contacts.get(i)!!)

            }
            contactsItem.forEach {
                val model = ContactMapper.getModelFromPOJO(it)
                realm.beginTransaction()
                realm.insertOrUpdate(model)
                realm.commitTransaction()

            }
        } finally {
            realm.close()
            getListFromDb()
        }

    }

    private fun getListFromDb() {
        val realm = Realm.getInstance(AppController.getInstanse()!!.getRealmConfiguration())

        try {
            realm.beginTransaction()
            contactList = ContactMapper.convertModelToPOJO(realm.where(RealmContactList::class.java).findAll()) as ArrayList<ContactList>
            realm.commitTransaction()
        } finally {
            realm.close()
            mainView!!.onSuccess(contactList)
        }

    }
    }

