package com.example.habitsapp.ui.navigation

sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object AddTodo:Screen("add_todo_screen")
    object StartTodo:Screen("start_todo_screen/{itemId}"){
        fun getItemId(itemId:Int):String{
            return "start_todo_screen/$itemId"
        }
    }
    object Search:Screen("search_screen")
}


