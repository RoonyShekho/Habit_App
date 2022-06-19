package com.example.habitsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.domain.model.Habit

@Database(
    entities = [Habit::class, Completed::class],
    version = 1
)
abstract class HabitDatabase(
):RoomDatabase(){
    abstract val habitDao: HabitDao
}