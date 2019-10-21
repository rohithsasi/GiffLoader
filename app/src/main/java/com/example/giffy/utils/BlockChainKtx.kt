package com.example.giffy.utils

import com.example.giffy.GiffyApplication

fun Int.getString(): String {
    return GiffyApplication.APPLICATION.getString(this)
}
