package com.example.habitsapp.ui.presentation.screen.add_habit


sealed class AddTodoAction {
    object Add : AddTodoAction()
    data class EnteredTitle(val value:String): AddTodoAction()
    data class EnteredDuration(val value:String): AddTodoAction()
    data class EnteredHours(val hours:Long): AddTodoAction()
    data class EnteredMinutes(val minute:Long): AddTodoAction()
}