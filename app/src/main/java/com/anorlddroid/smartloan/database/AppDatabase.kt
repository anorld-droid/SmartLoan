package com.anorlddroid.smartloan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PersonEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun personDao() : PersonDAO
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "PersonDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }

    }
}