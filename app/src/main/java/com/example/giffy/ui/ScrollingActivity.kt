package com.example.giffy.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.giffy.R

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

//        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//
//        uiScope.launch(Dispatchers.Default) {
//            val res=GiffySearchApi.get().getChartData1()
//
//
//            withContext(Dispatchers.Main){
//                updateImage(res.data[1].images.original.url)
//            }
//
//        }


        //https://media2.giphy.com/media/BlVnrxJgTGsUw/100_s.gif
//        Picasso.get().load("https://reactiongifs.me/wp-content/uploads/2013/11/seinfeld-happy-dance-Jerry-seinfeld-Elaine-George-Costanza.gif").error(R.drawable.ic_launcher_background)
//            .transform(PicassoCircularTransform()).fit().noFade().into(imageViewTest)
     //   https://media1.giphy.com/media/XbxZ41fWLeRECPsGIJ/giphy.gif

       // https://media2.giphy.com/media/BlVnrxJgTGsUw/giphy-preview.gif

//
//        Glide.with(this)
//            .asGif()
//            .load("https://media2.giphy.com/media/BlVnrxJgTGsUw/giphy-preview.gif")
//            .into(imageViewTest);
}

    fun updateImage(url:String = "https://media2.giphy.com/media/BlVnrxJgTGsUw/giphy-preview.gif"){
        Glide.with(this)
            .asGif()
            .load(url)
            .into(imageViewTest);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
