package com.example.habitsapp.presentation.screen.home


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.presentation.screen.add_habit.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = mutableStateOf(TodoState())
    val state = _state

    private var getNotesJob: Job? = null


    init{
        getItems()
    }

    private fun getItems() {
        getNotesJob?.cancel()

        getNotesJob = repository.getAllTodos()
            .onEach {
                _state.value = state.value.copy(
                    todos = it
                )
            }.launchIn(viewModelScope)
    }

    fun onAction(action: TodoAction){
        when(action){
            is TodoAction.DeleteTodo -> deleteTodo(action)
            else -> {}
        }
    }



    private fun deleteTodo(action: TodoAction.DeleteTodo) {
        viewModelScope.launch {
            repository.deleteTodo(action.id)
        }
    }
}