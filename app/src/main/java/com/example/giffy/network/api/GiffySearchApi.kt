package com.example.giffy.network.api

import android.util.Log
import com.example.giffy.network.GiffyRestClient
import com.example.giffy.network.model.GiffySearchResult
import com.example.giffy.network.webservice.GiffyServiceApi
import kotlinx.coroutines.Deferred


internal interface GiffySearchApi {

    suspend fun getData(search: String):GiffySearchApiImpl.NetworkModel<GiffySearchResult>

    companion object {
        fun get(): GiffySearchApi {
            return GiffySearchApiImpl
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
internal object GiffySearchApiImpl : GiffySearchApi {

    override suspend fun getData(search: String): NetworkModel<GiffySearchResult> {
        //todo ui does not work with one
        val res=  GIFFY_SERVICE_API.getGiffySearchResults("229ac3e932794695b695e71a9076f4e5","25","0","G","en","" +
                search).execute {
            return NetworkModel(null, it)
        }
        return NetworkModel(res)
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

    data class NetworkModel<T>(val result: T?, val error: Throwable? = null)

}



