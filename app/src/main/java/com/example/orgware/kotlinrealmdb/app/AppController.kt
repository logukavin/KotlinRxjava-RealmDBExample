package com.example.orgware.kotlinrealmdb.app

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppController : Application() {

    companion object {
        var appController: AppController? = null

        fun getInstanse(): AppController? {
            return appController
        }
    }

    private var appRepo: AppRepo? = null
    private var gson: Gson? = null
    private var realmConfiguration: RealmConfiguration? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realmConfiguration = RealmConfigurationFactory.createAdminRealmRealmConfiguration()
        appController = this
        gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

    }


    fun getappRepo(): AppRepo {
        if (appRepo == null) appRepo = createappRepo()
        return this.appRepo!!
    }

    fun getRealmConfiguration(): RealmConfiguration {
        if (realmConfiguration == null) realmConfiguration = RealmConfigurationFactory.createAdminRealmRealmConfiguration()
        return realmConfiguration!!
    }
    private fun createappRepo(): AppRepo {
        val retrofit: Retrofit
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
        retrofit = Retrofit.Builder().client(httpClient)
                .baseUrl(ApiUrl.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson!!)).build()

        return AppRepo(retrofit.create(ApiInterface::class.java), this.realmConfiguration)
    }
}