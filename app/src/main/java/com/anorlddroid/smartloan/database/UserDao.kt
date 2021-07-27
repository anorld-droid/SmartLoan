package com.anorlddroid.smartloan.database

import androidx.room.*
import java.util.ArrayList

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(vararg userEntity: UserEntity)

    @Query("SELECT id, phoneNumber, password FROM users")
    fun getLogInInfo() : List<UserEntity>

    @Query("SELECT phoneNumber FROM users")
    fun getPhoneNumber() : ArrayList<String>

    @Query("SELECT * FROM users")
    fun getAllInfo() : List<UserEntity>

//    fun insertAll(vararg personalInfo : PersonEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerPhoneNumber(phoneNumber : UserEntity)
    @Delete
    fun delete(personalInfo: UserEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateInfo(vararg personalInfo: UserEntity )
}