package com.example.habitsapp.presentation.screen.add_habit

import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.util.Utility

data class TodoState(
    val title:String = "",
    val duration:Long = Utility.TIME_COUNTDOWN,
    val todos:List<Habit> = emptyList(),
    val celebrate:Boolean = false,
    val progress:Float = 0f,
    val isPlaying:Boolean = false,
    val hours:Long = 0,
    val minutes:Long = 0,
    val isCompleted:Boolean = false
)
