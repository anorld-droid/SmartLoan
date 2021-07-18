package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _navigateToSignUpPaymentFragment = MutableLiveData<Boolean>()
    val navigateToSignUpPaymentFragment: LiveData<Boolean>
        get() = _navigateToSignUpPaymentFragment

    fun onFabClicked() {
            _navigateToSignUpPaymentFragment.value = true

    }

    fun onNavigatedToSignUpPaymentFragment() {
        _navigateToSignUpPaymentFragment.value = false
    }
}