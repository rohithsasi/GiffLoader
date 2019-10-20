package com.example.giffy.network.api

import com.example.giffy.network.GiffyRestClient
import com.example.giffy.network.model.GiffySearchResult
import com.example.giffy.network.webservice.GiffyServiceApi
import kotlinx.coroutines.Deferred


internal interface GiffyTrendingApi {

    suspend fun getData():GiffySearchResult

    companion object {
        fun get(): GiffyTrendingApi {
            return GiffyTrendingApiImpl
        }
    }
}

/**
 * Rest client
 */
private val GIFFY_SERVICE_API: GiffyServiceApi by lazy {
    GiffyRestClient.get().giffyServiceApi
}

/**
 * Makes network requests to the block chain apis and returns a result as an observable. The result is communicated
 * back to the repository layer (up the chain) using a network result listener (simple interface callbacks)
 */
private object GiffyTrendingApiImpl : GiffyTrendingApi {

    override suspend fun getData(): GiffySearchResult {

        val res=  GIFFY_SERVICE_API.getGiffyTrendingResults("229ac3e932794695b695e71a9076f4e5","10","0","G","en").execute {

            // networkListener.onSuccess(GiffySearchResult("","","","","", emptyList()))

        }
        return res!!
    }


    //TODO move the below functions to utility class as they are being used in multiple locations
    suspend inline infix fun <T> Deferred<T>.execute(onError: (Throwable) -> Unit): T? {
        return try {
            this.await()
        } catch (error: Throwable) {
            onError(error)
            null
        }
    }

}



