package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpPaymentViewModel : ViewModel() {

    private val _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean>
        get() = _exit

    fun onCloseClicked() {
        _exit.value = true

    }

    fun onExit() {
        _exit.value = false
    }
}