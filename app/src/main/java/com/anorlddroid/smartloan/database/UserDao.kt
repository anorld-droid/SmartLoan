package com.anorlddroid.smartloan.database

import androidx.room.*
import java.util.ArrayList

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(vararg userEntity: UserEntity)

    @Query("SELECT id, phoneNumber, password, paymentStatus FROM personal_information")
    fun getLogInInfo() : List<UserEntity>

    @Query("SELECT phoneNumber FROM personal_information")
    fun getPhoneNumber() : List<UserEntity>

    @Query("SELECT * FROM personal_information")
    fun getAllInfo() : List<UserEntity>

    @Query("SELECT paymentStatus FROM personal_information")
    fun getPaymentStatus(): List<UserEntity>

    @Query("UPDATE personal_information SET paymentStatus = :value WHERE id = 2")
    fun updatePaymentStatus(value : Int )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun paymentStatus(paymentStatus: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerPhoneNumber(phoneNumber : UserEntity)
    @Delete
    fun delete(personalInfo: UserEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateInfo(vararg personalInfo: UserEntity )
}