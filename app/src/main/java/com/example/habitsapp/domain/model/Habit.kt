package com.example.habitsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "habit_table"
)
data class Habit(
    @PrimaryKey(autoGenerate = false)
    var id:Int? = null,
    var title:String = "",
    var hours:Long = 0,
    var minutes:Long = 0
)