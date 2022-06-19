package com.example.habitsapp.util

import android.content.Context
import android.widget.Toast
import java.util.concurrent.TimeUnit

object Utility {

    //time to countdown - 1hr - 60secs
    const val TIME_COUNTDOWN: Long = 60000L
    private const val TIME_FORMAT = "%02d:%02d:%02d"


    //convert time to milli seconds
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toHours(this),
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )

    fun hoursToMs(hours: Int): Long {
        return hours.toLong() * 60 * 60 * 1000
    }

     fun minutesToMs(minutes: Int): Long {
        return minutes.toLong() * 60 * 1000
    }

    fun showToastMessage(message:String, context: Context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun isNumberValid(hours:Int, minutes:Int):Boolean{
        if(hours == 0 && minutes == 0){
            return false
        }

        return true
    }
}