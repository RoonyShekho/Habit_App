package com.example.habitsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "habit_table"
)
data class Habit(
    @PrimaryKey
    val id:Int? = null,
    val title:String = "",
    val hours:Long = 0,
    val minutes:Long = 0
)