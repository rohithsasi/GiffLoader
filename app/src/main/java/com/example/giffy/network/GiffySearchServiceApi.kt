package com.example.giffy.network

import com.example.giffy.network.model.GiffySearchResult
import com.example.giffy.network.model.GiffyTrendingResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val CHARTS = "charts/{chartType}"
const val STATS = "v1/gifs/search"

interface GiffyServiceApi {

    @GET(CHARTS)
    @Headers(value = ["Accept:application/json; charset=utf-8", "Accept-Charset:utf-8"])
    fun getCharts(
        /*@Query("chartName") chartName: String,*/
        @Path("chartType") chartType: String,
        @Query("timespan") timespan: String,
        @Query("rollingAverage") rollingAverage: String
        /* @Path("start") start: String,
         @Path("format") format: String
         @Path("sampled") sampled: String*/
    ): Response<GiffySearchResult>


    @GET(STATS)
    @Headers(value = ["Accept:application/json; charset=utf-8", "Accept-Charset:utf-8"])
    fun getBlockChainStats(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("q") rollingqAverage: String

    ) : Deferred<GiffyTrendingResult>

    @GET(STATS)
    @Headers(value = ["Accept:application/json; charset=utf-8", "Accept-Charset:utf-8"])
    fun getGiffySearchResults(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("q") rollingqAverage: String

    ) : Deferred<GiffySearchResult>

}