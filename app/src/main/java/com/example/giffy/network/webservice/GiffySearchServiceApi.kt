package com.example.giffy.network.webservice

import com.example.giffy.network.model.GiffySearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val SEARCH = "v1/gifs/search"
const val TRENDING = "v1/gifs/trending"

internal interface GiffyServiceApi {
    @GET(SEARCH)
    @Headers(value = ["Accept:application/json; charset=utf-8", "Accept-Charset:utf-8"])
    fun getGifsSearchResultsAsync(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("q") searchParam: String

    ): Deferred<GiffySearchResult>
}