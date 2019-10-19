package com.example.giffy.repository

import com.example.giffy.network.GiffySearchApi
import com.example.giffy.network.toGiffyImageResults


/**
 * The data repository layer which proccesses the data that the ui expects caches(shared pref used for cache for now,
 * could be a database as well) it and communicates the proccessed result after caching to the presentation layer using
 * result listeners(simple callbacks).
 */
interface BlockChainDataRepository {
    suspend fun getData(search:String): GiffyResult

    companion object {
        fun get(): BlockChainDataRepository {
            return BlockChainDataRepositoryImpl
        }
    }
}

internal object BlockChainDataRepositoryImpl : BlockChainDataRepository {

    internal var giffySearchApi: GiffySearchApi = GiffySearchApi.get()

    //todo
    override suspend fun getData(search: String): GiffyResult {
        val res = search.let { giffySearchApi.getData(it).toGiffyImageResults() }
        //todo clean
        return if (!res.urlList.isEmpty()) return OnSuccessGiffyResult(
            res
        ) else OnFailurGiffyResult(
            IllegalAccessException()
        )
    }
}