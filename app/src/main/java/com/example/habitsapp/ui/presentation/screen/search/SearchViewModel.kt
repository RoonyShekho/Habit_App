package com.example.habitsapp.ui.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.ui.presentation.screen.add_habit.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    private val _query = mutableStateOf("")
    val query = _query

    private val _state = mutableStateOf(TodoState())
    val state = _state

    fun searchHabits(title:String) {
        viewModelScope.launch {
            repository.searchHabits(title).collect {
                _state.value = _state.value.copy(
                    habits = it
                )
            }
        }
    }

    fun setQuery(value:String){
        _query.value = value
    }


     fun deleteTodo(id:Int) {
        viewModelScope.launch {
            repository.deleteTodo(id)
        }
    }
}