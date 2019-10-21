package com.example.giffy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giffy.R
import com.example.giffy.coroutine.MainCoroutineScope
import com.example.giffy.utils.NetworkConnectionUtil
import com.example.giffy.utils.getString
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private val uiScope = MainCoroutineScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        launchHome()
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(uiScope)
    }

    private fun launchHome() {
        /**
         * A simple usage of Kotlin co routines for ui loading .The thought here is perform all onboarding operations
         * ui or computational or network in co routine. For now put a delay to simulate a temporary loading experience
         */
        uiScope.launch(Dispatchers.Main) {
            if (NetworkConnectionUtil.isOnline(this@SplashScreenActivity)) {
                delay(1_000)
                HomeActivity.navigateTo(this@SplashScreenActivity)
                finish() //This need to finish
            } else {
                throwErrorDialog()
            }
        }
    }

    private fun throwErrorDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.network_dialog_title.getString())
            .setMessage(R.string.network_dialog_text.getString())
            .setPositiveButton(R.string.network_dialog_retry.getString()) { dialog, which ->
                launchHome()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
                finish()
            }
            .show()
    }

}
