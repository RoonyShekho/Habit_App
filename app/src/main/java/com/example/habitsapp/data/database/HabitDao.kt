package com.example.habitsapp.data.database

import androidx.room.*
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.domain.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {


    @Query("SELECT * FROM habit_table")
    fun getAllHabits():Flow<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHabit(todo: Habit)

    @Query("SELECT * FROM habit_table WHERE id = :id")
    suspend fun getHabitById(id:Int): Habit?

    @Query("DELETE FROM habit_table WHERE id=:id")
    suspend fun deleteHabit(id:Int?)

    @Query("DELETE FROM completed_table WHERE id=:id")
    fun deleteCompleted(id:Int)

    @Query("SELECT * FROM completed_table ORDER BY id ASC")
    fun getCompletedHabits():Flow<List<Completed>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCompletedHabit(completed: Completed)
}