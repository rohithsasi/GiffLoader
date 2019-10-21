package com.example.giffy.repository

import com.example.giffy.network.api.GiffySearchApi

interface GiffyDataRepository {
    suspend fun getGifs(search:String): GiffyResult

    companion object {
        fun get(): GiffyDataRepository {
            return GiffyDataRepositoryImpl
        }
    }
}

internal object GiffyDataRepositoryImpl : GiffyDataRepository {
    internal var giffySearchApi: GiffySearchApi = GiffySearchApi.get()

    override suspend fun getGifs(search: String): GiffyResult  {
        val res = giffySearchApi.getGifs(search)
        res.result?.toGiffyImageResults()?.let {
            if(!it.urlList.isNullOrEmpty()) return OnSuccessGiffyResult(it)
        }
        return OnFailurGiffyResult(Throwable("No Giffy Urls Found",res.error))
    }
}