package com.example.giffy.network.model

import com.google.gson.annotations.SerializedName


data class GiffySearchResult(
        @SerializedName("data") val data: List<Data>)

data class Data(
        @SerializedName("type") val gif: String,
        @SerializedName("id") val id: String,
        @SerializedName("images") val images: Images
)

//data class Data(
//        @SerializedName("images") val images: Images
//)

data class Images(
        @SerializedName("preview_gif") val original: PreviewGif
)

data class PreviewGif(
        @SerializedName("height") val height : String,
        @SerializedName("size") val size: String,
        @SerializedName("url") val url: String,
        @SerializedName("width") val width: String
)

