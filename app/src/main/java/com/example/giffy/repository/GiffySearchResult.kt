package com.example.giffy.repository

import android.os.Parcelable
import com.example.giffy.model.GiffyImageResults
import kotlinx.android.parcel.Parcelize

//interface BlockChainResultListener<Result> {
//
//    fun onEvent(result: RoBitcoinResult<Result>)
//
//}
//
//inline infix fun <reified Result> BlockChainResultListener<Result>.onSuccess(result: Result) {
//    onEvent(OnSuccessRoBitcoinResult(result))
//}
//
//inline infix fun <reified Result> BlockChainResultListener<Result>.onFailure(result: Throwable) {
//    onEvent(OnFailureRoBitcoinResult(result))
//}
//
//@Suppress("unused")
//
//sealed class RoBitcoinResult<Result>
//
//data class OnSuccessRoBitcoinResult<Result>(val result: Result) : RoBitcoinResult<Result>()
//
//data class OnFailureRoBitcoinResult<Result>(val throwable: Throwable) : RoBitcoinResult<Result>()

interface  ActionResult :Parcelable

sealed class GiffyResult:ActionResult

@Parcelize
data class OnSuccessGiffyResult(val result: GiffyImageResults?, val throwable: Throwable? =null) : GiffyResult()

@Parcelize
data class OnFailurGiffyResult(val throwable: Throwable? =null) : GiffyResult()
