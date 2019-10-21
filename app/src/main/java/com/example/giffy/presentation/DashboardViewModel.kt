package com.example.giffy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    /**
     * Live data emitting a simple url text to update the ui. Simple usage here to fit in the pattern of data coming
     * from the view model.
     */
    private val _text = MutableLiveData<String>().apply {
        value = "https://giphy.com/login"
    }
    val text: LiveData<String> = _text
}