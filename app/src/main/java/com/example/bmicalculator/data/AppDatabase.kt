package com.example.bmicalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BmiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bmiDao(): BmiDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bmi_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
