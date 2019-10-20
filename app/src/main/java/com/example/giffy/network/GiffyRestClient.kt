package com.example.giffy.network

import com.example.giffy.network.webservice.GiffyServiceApi
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal interface GiffyRestClient {
    val giffyServiceApi: GiffyServiceApi

    companion object {
        fun get(): GiffyRestClient {
            return GiffyRestClientImpl
        }
    }
}

private object GiffyRestClientImpl : GiffyRestClient {

    override val giffyServiceApi: GiffyServiceApi
        get() {
            return getGiffyApi()
        }

    private var identity: GiffyServiceApi? = null
    
    private fun getGiffyApi(): GiffyServiceApi {
        return identity
                ?: giffyApiBuilder(GiffyServiceApi::class.java).apply { identity = this }
    }

    private fun <T> giffyApiBuilder(clz: Class<T>, baseUrl: String = "https://api.giphy.com"): T {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
                .create(clz)
    }

    private fun getHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder().build()
    }

    private val okHttpClient: OkHttpClient?
        get() {
            return getHttpClient()
        }
}
