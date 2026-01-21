package com.example.bmicalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BmiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weight: Float,
    val height: Float,
    val bmi: Float
)
