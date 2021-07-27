package com.anorlddroid.smartloan.registration.signup

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anorlddroid.smartloan.database.UserDatabase
import com.anorlddroid.smartloan.database.UserEntity

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val userDatabase: UserDatabase
    private val context : Context

    init {
        this.context = application
        userDatabase = UserDatabase.getUserDatabase(context)!!
    }

    fun registerUser(userEntity: UserEntity): Unit? {
        return userDatabase.userDao()?.registerUser(userEntity)
    }
}