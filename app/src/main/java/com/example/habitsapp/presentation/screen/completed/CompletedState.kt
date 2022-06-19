package com.example.habitsapp.presentation.screen.completed

import com.example.habitsapp.domain.model.Completed

data class CompletedState(
    val id:Int? = null,
    val title:String = "",
    val duration:String = "",
    val completed:List<Completed> = emptyList()
)
