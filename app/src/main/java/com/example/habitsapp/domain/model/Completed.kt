package com.example.habitsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "completed_table"
)
data class Completed(
    @PrimaryKey
    val id:Int? = null,
    val title:String = "",
    val duration:String = ""
)
