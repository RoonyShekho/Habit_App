package com.example.habitsapp.presentation.screen.add_habit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _titleState = mutableStateOf(TodoState())
    val titleState = _titleState


    private val _durationState = mutableStateOf(TodoState())
    val durationState = _durationState



    fun onAction(action: AddTodoAction){
        when(action){
            is AddTodoAction.Add -> addTodo()

            is AddTodoAction.EnteredDuration -> {
                _durationState.value = durationState.value.copy(
                    duration = action.value.toLong()
                )
            }

            is AddTodoAction.EnteredTitle -> {
                _titleState.value= titleState.value.copy(
                    title = action.value
                )
            }
            is AddTodoAction.EnteredHours -> {
                _durationState.value = durationState.value.copy(
                    hours = action.hours
                )
            }
            is AddTodoAction.EnteredMinutes -> {
                _durationState.value = durationState.value.copy(
                    minutes = action.minute
                )
            }
        }
    }

    private fun addTodo() {
        viewModelScope.launch {
            viewModelScope.launch {
                    repository.insertTodo(
                        Habit(
                            title = titleState.value.title,
                            hours = durationState.value.hours,
                            minutes = durationState.value.minutes
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveTodo)
            }
        }
    }

}