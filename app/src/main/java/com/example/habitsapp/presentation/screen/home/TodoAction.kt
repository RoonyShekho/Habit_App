package com.example.habitsapp.presentation.screen.home

import com.example.habitsapp.domain.model.Habit

sealed class TodoAction{
    data class AddTodo(val value: Habit): TodoAction()
    data class DeleteTodo(val id:Int): TodoAction()
}
