package com.example.habitsapp.util

sealed class UiEvent{
    object SaveTodo:UiEvent()
    object SaveCompletedTodo:UiEvent()
}
