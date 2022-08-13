package com.example.habitsapp.navigation

sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object AddTodo:Screen("add_todo_screen")
    object StartTodo:Screen("start_todo_screen/{itemId}"){
        fun getItemId(itemId:Int):String{
            return "start_todo_screen/$itemId"
        }
    }
    object Completed:Screen("completed_screen")
    object Search:Screen("search_screen")
}