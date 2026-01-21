package com.example.bmicalculator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmiDao {
    @Insert
    suspend fun insert(bmi: BmiEntity)

    @Query("SELECT * FROM BmiEntity")
    fun getAll(): LiveData<List<BmiEntity>>

    @Query("DELETE FROM BmiEntity")
    suspend fun clearAll()
}
