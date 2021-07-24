package com.anorlddroid.smartloan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "personal_information")
data class PersonEntity  (
    @ColumnInfo(name = "firstName")
    var firstName: String? = null,

    @ColumnInfo(name = "lastName")
    var lastName: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "nationalID")
    var nationalID: Int? = null,

    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: Long? = null,

    @ColumnInfo(name = "password")
    var password: String? = null,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: Int? = null

){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}

