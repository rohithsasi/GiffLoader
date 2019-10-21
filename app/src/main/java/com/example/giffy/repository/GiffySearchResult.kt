package com.example.giffy.repository

import android.os.Parcelable
import com.example.giffy.model.GiffyImageResults
import kotlinx.android.parcel.Parcelize

interface  ActionResult :Parcelable

/**
 * Results that comes back from the api layer. Various ui updates can be made w.r.t the type GiffyResult
 * that comes back. Repos make respective transformations of the raw data before informing the ui.
 */
sealed class GiffyResult:ActionResult

@Parcelize
data class OnSuccessGiffyResult(val result: GiffyImageResults?, val throwable: Throwable? =null) : GiffyResult()

@Parcelize
data class OnFailureGiffyResult(val throwable: Throwable? =null) : GiffyResult()
