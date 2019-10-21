package com.example.giffy.network.api

import android.util.Log
import com.example.giffy.network.GiffyRestClient
import com.example.giffy.network.model.GiffySearchResult
import com.example.giffy.network.webservice.GiffyServiceApi
import kotlinx.coroutines.Deferred

/**
 * Api layer responsible for the network request
 */
internal interface GiffySearchApi {

    suspend fun getGifs(search: String):GiffySearchApiImpl.NetworkModel<GiffySearchResult>

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

internal object GiffySearchApiImpl : GiffySearchApi {

    override suspend fun getGifs(search: String): NetworkModel<GiffySearchResult> {
        val res=  GIFFY_SERVICE_API.getGifsSearchResultsAsync(
            "229ac3e932794695b695e71a9076f4e5","25","0","G","en",
            search).execute {
            Log.d("Giffy Exception","${it.message}")
            return NetworkModel(null, it)
        }
        return NetworkModel(res)
    }


    private suspend inline infix fun <T> Deferred<T>.execute(onError: (Throwable) -> Unit): T? {
        return try {
            this.await()
        } catch (error: Throwable) {
            onError(error)
            null
        }
    }

    /**
     * Wrappers for both errors and a successful response
     */
    data class NetworkModel<T>(val result: T?, val error: Throwable? = null)
}



