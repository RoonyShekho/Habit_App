package com.example.habitsapp.presentation.screen.completed


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitsapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = mutableStateOf(CompletedState())
    val state = _state

    private var getNotesJob: Job? = null


    fun deleteCompleted(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteCompleted(id)
        }
    }

    init {
        getItems()
    }

    private fun getItems() {
        getNotesJob?.cancel()

        getNotesJob = repository.getCompletedItems()
            .onEach {
                _state.value = state.value.copy(
                    completed = it
                )
            }.launchIn(viewModelScope)
    }

}