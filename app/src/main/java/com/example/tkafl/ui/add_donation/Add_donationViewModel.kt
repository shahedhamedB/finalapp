package com.example.tkafl.ui.add_donation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Add_donationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "اضافة تبرع"
    }
    val text: LiveData<String> = _text
}