package com.example.habitsapp.data.repository


import com.example.habitsapp.data.database.HabitDao
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val habitDao: HabitDao
) {


    suspend fun insertTodo(todo: Habit) {
        habitDao.insertHabit(todo)
    }

    suspend fun getItem(id: Int): Habit? {
        return habitDao.getHabitById(id)
    }

    fun getCompletedItems(): Flow<List<Completed>> {
        return habitDao.getCompletedHabits()
    }

    suspend fun addCompletedItem(completed: Completed) {
        habitDao.addCompletedHabit(completed)
    }

    fun deleteCompleted(id:Int){
        habitDao.deleteCompleted(id)
    }

    fun getAllTodos(): Flow<List<Habit>> {
        return habitDao.getAllHabits()
    }


    suspend fun deleteTodo(id:Int) {
        habitDao.deleteHabit(id)
    }

}