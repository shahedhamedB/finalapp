package com.example.tkafl.ui.my_alms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class My_almsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "صدقاتي"
    }
    val text: LiveData<String> = _text
}