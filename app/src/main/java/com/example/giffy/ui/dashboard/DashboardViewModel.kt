package com.example.giffy.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "https://giphy.com/login"
    }
    val text: LiveData<String> = _text
}