package com.example.giffy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GiffyImageResults(
    val urlList: List<Image>
) : Parcelable

@Parcelize
data class Image(
    val height: String,
    val width: String,
    val url: String
): Parcelable