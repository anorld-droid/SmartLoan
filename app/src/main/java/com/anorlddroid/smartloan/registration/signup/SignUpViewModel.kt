package com.anorlddroid.smartloan.registration.signup

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.anorlddroid.smartloan.database.AppDatabase
import com.anorlddroid.smartloan.database.PersonEntity
import com.anorlddroid.smartloan.repository.PersonRepository

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val personRepository: PersonRepository
    private var mLiveData : LiveData<List<PersonEntity>>? = null
    private val context : Context

    init {
        this.context = application
        personRepository = PersonRepository(context)
    }


    fun insertInfo(personEntity: PersonEntity)  {
        return personRepository.insertInfo(personEntity)
    }


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