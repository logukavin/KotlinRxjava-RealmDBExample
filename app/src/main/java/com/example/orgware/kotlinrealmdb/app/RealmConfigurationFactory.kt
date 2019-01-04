package com.example.orgware.kotlinrealmdb.app

import io.realm.RealmConfiguration

class RealmConfigurationFactory {
    companion object {
        fun createAdminRealmRealmConfiguration(): RealmConfiguration? {
            return RealmConfiguration.Builder().name("ContactList").deleteRealmIfMigrationNeeded().build()
        }
    }
}