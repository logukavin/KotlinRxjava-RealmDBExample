package com.example.orgware.kotlinrealmdb.realmmodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

open class RealmContactList(var name: String? = "",var phone: String? = "") : RealmObject() {

}