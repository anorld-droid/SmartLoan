package com.anorlddroid.smartloan.mainui.myprofile


import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anorlddroid.smartloan.database.UserDatabase
import com.anorlddroid.smartloan.database.UserEntity

class MyProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userDatabase: UserDatabase
    private val context : Context

    private val _saveChanges = MutableLiveData<Boolean>()
    val saveChanges: LiveData<Boolean>
        get() = _saveChanges
    private val _infoUpdated = MutableLiveData<Boolean>()
    val infoUpdated: LiveData<Boolean>
        get() = _infoUpdated


    init {
        this.context = application
        userDatabase = UserDatabase.getUserDatabase(context)!!
        _infoUpdated.value = true
    }



    fun insertUser(userEntity: UserEntity): Unit? {
        return userDatabase.userDao()?.registerUser(userEntity)
    }
    fun deleteUser(userEntity: UserEntity): Unit? {
        return userDatabase.userDao()?.delete(userEntity)
    }

    fun getAllInfo() :List<UserEntity>? {
        return userDatabase.userDao()?.getAllInfo()

    }

    fun onSaveChangesClicked() {
        _saveChanges.value = true

    }

    fun onInfoUpdated() {
        _infoUpdated.value = true
    }
    fun uiUpdated(){
        _infoUpdated.value = false

    }

}