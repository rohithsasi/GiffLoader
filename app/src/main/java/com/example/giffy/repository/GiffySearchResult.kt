package com.example.giffy.repository

import android.os.Parcelable
import com.example.giffy.model.GiffyImageResults
import kotlinx.android.parcel.Parcelize

interface  ActionResult :Parcelable

sealed class GiffyResult:ActionResult

@Parcelize
data class OnSuccessGiffyResult(val result: GiffyImageResults?, val throwable: Throwable? =null) : GiffyResult()

@Parcelize
data class OnFailurGiffyResult(val throwable: Throwable? =null) : GiffyResult()
