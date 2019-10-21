package com.example.giffy.repository

import com.example.giffy.network.api.GiffySearchApi

/**
 * Repository that fetches network data and emits results processed for ui to the view model
 */
interface GiffyDataRepository {
    suspend fun getGifs(search:String): GiffyResult

    companion object {
        fun get(): GiffyDataRepository {
            return GiffyDataRepositoryImpl
        }
    }
}

internal object GiffyDataRepositoryImpl : GiffyDataRepository {
    private var giffySearchApi: GiffySearchApi = GiffySearchApi.get()

    /**
     * Informs the view model of Success/Failure with the respective processed data
     */
    override suspend fun getGifs(search: String): GiffyResult  {
        val res = giffySearchApi.getGifs(search)
        res.result?.toGiffyImageResults()?.let {
            if(!it.urlList.isNullOrEmpty()) return OnSuccessGiffyResult(it)
        }
        return OnFailureGiffyResult(Throwable("No Giffy Urls Found",res.error))
    }
}