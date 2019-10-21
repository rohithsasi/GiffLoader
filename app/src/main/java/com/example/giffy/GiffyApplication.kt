package com.example.giffy
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.giffy.utils.StethoUtils

class GiffyApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        application = this
        initStetho()
    }

    private fun initStetho() {
        StethoUtils.init(this)
    }

    companion object {
        private lateinit var application: Application
        val APPLICATION by lazy { application }
    }
}