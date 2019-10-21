package com.example.giffy.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.giffy.R
import com.example.giffy.utils.NetworkConnectionUtil
import com.example.giffy.utils.getString
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_scrolling.*

class HomeActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

       if(!NetworkConnectionUtil.isOnline(this@HomeActivity)){
           throwErrorDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard
               // R.id.navigation_notifications//to be implemented
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
}
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    private fun throwErrorDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.network_dialog_title.getString())
            .setMessage(R.string.network_dialog_text.getString())
            .setPositiveButton(R.string.network_dialog_retry.getString()){ dialog, which ->
                if(!NetworkConnectionUtil.isOnline(this@HomeActivity)){
                    throwErrorDialog()
                }

                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {

        fun navigateTo(activity: Context) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))

        }
    }
}
