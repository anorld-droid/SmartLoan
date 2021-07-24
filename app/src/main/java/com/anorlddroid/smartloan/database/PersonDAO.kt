package com.anorlddroid.smartloan.database

import androidx.room.*

@Dao
interface PersonDAO {
    @Query("SELECT * FROM personal_information")
    fun getAllInfo() : List<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg personalInfo : PersonEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoneNumber(phoneNumber : PersonEntity)
    @Delete
    fun delete(personalInfo: PersonEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateInfo(vararg personalInfo: PersonEntity )
}