package com.anorlddroid.smartloan.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.anorlddroid.smartloan.database.AppDatabase
import com.anorlddroid.smartloan.database.PersonDAO
import com.anorlddroid.smartloan.database.PersonEntity
import java.util.concurrent.ExecutionException

class PersonRepository(context: Context) {

    companion object {
        private lateinit var personDAO: PersonDAO
    }
    init {
        val appDatabase = AppDatabase.getInstance(context)
        personDAO = appDatabase.personDao()
    }


    fun  insertInfo(personEntity: PersonEntity) = personDAO.insertAll(personEntity)
    fun  insertPhoneNumber(phoneNumber: PersonEntity) = personDAO.insertPhoneNumber(phoneNumber)

    fun  deleteInfo(personEntity: PersonEntity) = personDAO.delete(personEntity)
    fun  updateInfo(personEntity: PersonEntity) = personDAO.updateInfo(personEntity)

    fun getAllInfo(): List<PersonEntity> = personDAO.getAllInfo()
}