package com.example.giffy.network

import com.example.giffy.model.GiffyImageResults
import com.example.giffy.model.Image
import com.example.giffy.network.model.GiffySearchResult

fun GiffySearchResult.toGiffyImageResults(): GiffyImageResults {
    val result = mutableListOf<Image>()
    this.data.forEach {
        //result.add(it.images.original.url)
        it.images.original.run {
            result.add(Image(height,width,url))
        }

    }
    return GiffyImageResults(result)
}

