package com.example.orgware.kotlinrealmdb.app

import com.example.orgware.kotlinrealmdb.model.ContactResponse
import retrofit2.http.GET
import rx.Observable


interface ApiInterface {

    @GET("contacts/")
    fun getSampleApi(): Observable<ContactResponse>


}