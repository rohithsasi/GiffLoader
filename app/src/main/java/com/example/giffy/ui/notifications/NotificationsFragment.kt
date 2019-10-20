package com.example.giffy.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.giffy.R
import com.example.myapplication.ui.notifications.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        setHasOptionsMenu(true)

        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        activity!!.invalidateOptionsMenu()
        menu.findItem(R.id.app_bar_search).setVisible(false)
    }
}