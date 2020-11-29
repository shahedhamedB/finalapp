package com.example.tkafl.ui.aidrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AidrequestViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "تقديم طلب معونة"
    }
    val text: LiveData<String> = _text

}