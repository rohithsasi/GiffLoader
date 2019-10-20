package com.example.giffy.repository

import com.example.giffy.model.GiffyImageResults
import com.example.giffy.model.Image
import com.example.giffy.network.model.GiffySearchResult

internal fun GiffySearchResult.toGiffyImageResults(): GiffyImageResults {
    val result = mutableListOf<Image>()
    this.data.forEach {
        it.images.original.run {
            result.add(Image(height,width,url))
        }

    }
    return GiffyImageResults(result)
}

