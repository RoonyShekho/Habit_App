package com.example.habitsapp.ui.presentation.screen.start

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.*
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.ui.presentation.screen.add_habit.TodoState
import com.example.habitsapp.util.Utility
import com.example.habitsapp.util.Utility.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: Repository,
    private val notificationManager: NotificationManagerCompat,
    private val notificationBuilder: NotificationCompat.Builder,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val countDownTimer = mutableStateOf<CountDownTimer?>(null)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog


    private val _time = MutableLiveData((1000L).formatTime())
    val time: LiveData<String> = _time

    private val _isCompleted = MutableLiveData(false)
    val isCompleted: LiveData<Boolean> = _isCompleted

    private val _duration = MutableLiveData<Long>(1)
    val duration: LiveData<Long> = _duration

    private val itemTitle = mutableStateOf("")

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

            val totalDuration = (item?.hours ?: 0) + (item?.minutes ?: 1)

            itemTitle.value = item!!.title

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
        stopNotification()
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

                    startNotification(millisRemaining.formatTime())

                } catch (e: NumberFormatException) {
                    Log.d("ViewModel", e.message.toString())
                }
            }


            override fun onFinish() {
                val currentTime = System.currentTimeMillis()
                val time = Utility.getTime(currentTime)

                pauseTimer()
                viewModelScope.launch(Dispatchers.IO) {
                    repository.addCompletedItem(
                        Completed(
                            title = progressState.value!!.title,
                            duration = duration.value!!.formatTime(),
                            date = time
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

            // if the count down is playing
            //and back button is pressed then show a dialog
            StartAction.ShowDialog -> _showDialog.value = true
        }
    }


    private fun handleTimerValues(
        isPlaying: Boolean,
        text: String,
        progress: Float
    ) {

        _progressState.value = progressState.value!!.copy(
            isPlaying = isPlaying
        )

        _time.value = text


        _progressState.value = progressState.value!!.copy(
            progress = progress
        )
    }

    private fun stopNotification(){
        notificationManager.cancel(1)
    }

    private fun startNotification(time:String) {
        notificationManager.notify(
            1,
            notificationBuilder.setContentTitle("Habit ${itemTitle.value} Started")
                .setContentText(time)
                .build()
        )
    }
}