package com.superclock.release1.ui.stopwatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StopwatchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is stopwatch Fragment"
    }
    val text: LiveData<String> = _text
}