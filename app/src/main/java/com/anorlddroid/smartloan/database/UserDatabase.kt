package com.anorlddroid.smartloan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private const val dbName = "user"
        private var userDatabase: UserDatabase? = null
        @Synchronized
        fun getUserDatabase(context: Context?): UserDatabase? {
            if (userDatabase == null) {
                userDatabase = Room.databaseBuilder(context!!, UserDatabase::class.java, dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return userDatabase
        }
    }
}