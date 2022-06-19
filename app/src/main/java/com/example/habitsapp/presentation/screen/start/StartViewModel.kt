package com.example.habitsapp.presentation.screen.start

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.presentation.screen.add_habit.TodoState
import com.example.habitsapp.util.Utility.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val countDownTimer = mutableStateOf<CountDownTimer?>(null)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog :LiveData<Boolean> = _showDialog


    private var initialValue:Long = 0

    private val _time = MutableLiveData(initialValue.formatTime())
    val time: LiveData<String> = _time

    private val _isCompleted = MutableLiveData(false)
    val isCompleted:LiveData<Boolean> = _isCompleted

    private val _duration = MutableLiveData<Long>(0)
    val duration: LiveData<Long> = _duration


    private val _progressState = MutableLiveData(TodoState())
    val progressState: LiveData<TodoState> = _progressState


    private var currentItemId: Int? = null

    init {
        viewModelScope.launch(Dispatchers.Main) {
            //passing  id to screen
            val itemId = savedStateHandle.get<Int>("itemId")

            val item = itemId?.let { repository.getItem(it) }

            currentItemId = item?.id

            _progressState.value = progressState.value?.copy(
                title = item?.title ?: ""
            )

            initialValue = (item?.hours)?.plus((item.minutes)) ?: 1


            val totalDuration = (item?.hours ?: 0) + (item?.minutes ?: 1)

            _duration.postValue(totalDuration)
        }
    }


    fun handleCountDownTimer() {
        if (progressState.value!!.isPlaying) {
            pauseTimer()
        } else {
            startTimer()
        }
    }


    private fun pauseTimer() {
        countDownTimer.value?.cancel()


        _time.value?.let {
            handleTimerValues(
                false,
                it,
                progressState.value?.progress ?: 1f
            )
        }
    }

    private fun startTimer() {
        _progressState.value = progressState.value!!.copy(
            isPlaying = true
        )

        countDownTimer.value = object : CountDownTimer(duration.value!!, 1000) {

            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / (duration.value!!)

                try {
                    handleTimerValues(
                        true,
                        millisRemaining.formatTime(),
                        progressValue
                    )
                } catch (e: NumberFormatException) {
                    Log.d("ViewModel", e.message.toString())
                }
            }


            override fun onFinish() {
                pauseTimer()
                viewModelScope.launch(Dispatchers.IO) {
                    repository.addCompletedItem(
                        Completed(
                            title = progressState.value!!.title,
                            duration = duration.value!!.formatTime()
                        )
                    )

                    _isCompleted.postValue(true)
                }
            }
        }.start()
    }


        fun onAction(action: StartAction) {
            when (action) {
                StartAction.Delete -> {
                    viewModelScope.launch {
                        repository.deleteTodo(currentItemId!!)
                    }
                }
                StartAction.HideDialog -> _showDialog.value = false

                StartAction.ShowDialog -> _showDialog.value = true
            }
        }


    private fun handleTimerValues(
        isPlaying1: Boolean,
        text: String,
        progress: Float
    ) {

        _progressState.value = progressState.value!!.copy(
            isPlaying = isPlaying1
        )

        _time.value = text


        _progressState.value = progressState.value!!.copy(
            progress = progress
        )


    }


}