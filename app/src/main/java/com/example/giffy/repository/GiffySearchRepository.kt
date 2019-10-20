package com.example.giffy.repository

import com.example.giffy.network.api.GiffySearchApi
import com.example.giffy.network.api.GiffyTrendingApi


/**
 * The data repository layer which proccesses the data that the ui expects caches(shared pref used for cache for now,
 * could be a database as well) it and communicates the proccessed result after caching to the presentation layer using
 * result listeners(simple callbacks).
 */
interface GiffyDataRepository {
    suspend fun getData(search:String): GiffyResult

    suspend fun getTrendingData(): GiffyResult

    companion object {
        fun get(): GiffyDataRepository {
            return GiffyDataRepositoryImpl
        }
    }
}

internal object GiffyDataRepositoryImpl : GiffyDataRepository {

    internal var giffyTrendingApi: GiffyTrendingApi = GiffyTrendingApi.get()
    override suspend fun getTrendingData(): GiffyResult {
        val res = giffyTrendingApi.getData().toGiffyImageResults()
        //todo clean
        return if (!res.urlList.isEmpty()) return OnSuccessGiffyResult(
            res
        ) else OnFailurGiffyResult(
            IllegalAccessException()
        )

    }

    internal var giffySearchApi: GiffySearchApi = GiffySearchApi.get()

    override suspend fun getData(search: String): GiffyResult  {
        val res = giffySearchApi.getData(search).result
        res?.toGiffyImageResults()?.let {
            if(!it.urlList.isNullOrEmpty()) return OnSuccessGiffyResult(it)
        }
        return OnFailurGiffyResult(Throwable("No Giffy Urls Found"))
    }
}