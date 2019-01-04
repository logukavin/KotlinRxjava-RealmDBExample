package com.example.orgware.kotlinrealmdb.app

import com.example.orgware.kotlinrealmdb.model.ContactResponse
import com.example.orgware.kotlinrealmdb.utils.RxJavaUtils
import io.realm.RealmConfiguration
import rx.Observable


class AppRepo(appApi: ApiInterface, realmConfiguration: RealmConfiguration?) {

    private var appApi: ApiInterface
    private var realmConfiguration: RealmConfiguration



    init {
        this.realmConfiguration = realmConfiguration!!
        this.appApi = appApi
    }

    fun getContact(): Observable<ContactResponse> {
        return appApi.getSampleApi().compose(RxJavaUtils.applyErrorTransformer())
            .map { it }
    }


}