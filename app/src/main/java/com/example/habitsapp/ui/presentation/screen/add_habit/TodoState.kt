package com.example.habitsapp.ui.presentation.screen.add_habit

import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.util.Utility
import java.util.Collections.emptyList


data class TodoState(
    val title:String = "",
    val duration:Long = Utility.TIME_COUNTDOWN,
    val habits:List<Habit> = emptyList(),
    val celebrate:Boolean = false,
    val progress:Float = 0f,
    val isPlaying:Boolean = false,
    val hours:Long = 0,
    val minutes:Long = 60000,
    val isCompleted:Boolean = false
)