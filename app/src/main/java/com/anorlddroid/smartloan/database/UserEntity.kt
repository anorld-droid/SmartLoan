package com.anorlddroid.smartloan.database

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "personal_information")
class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String? = null

    @ColumnInfo(name = "firstName")
    var firstName: String? = null

    @ColumnInfo(name = "lastName")
    var lastName: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "nationalID")
    var nationalID: Long? = null

    @ColumnInfo(name = "password")
    var password: String? = null

    @ColumnInfo(name = "paymentStatus")
    var paymentStatus: Int? = null
}