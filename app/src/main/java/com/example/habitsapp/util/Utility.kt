package com.example.habitsapp.util

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Utility {

    //time to countdown - 1hr - 60secs
    const val TIME_COUNTDOWN: Long = 60000L
    private const val TIME_FORMAT = "%02d:%02d:%02d"


    const val TWEEN_DURATION = 650


    fun navigateTo(destination:String, navController: NavHostController){

        navController.navigate(destination){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

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

     fun getTime(millis:Long):String{
        val dateFormat = "dd MMMM"
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.timeInMillis = millis

        return formatter.format(calendar.time)
    }

    fun isNumberValid(hours:Int, minutes:Int):Boolean{
        var isValid = true
        if(hours == 0 && minutes == 0){
            isValid = false
        }
        return isValid
    }


    fun formatTime(seconds: String, minutes: String, hours: String): String {
        return "$hours:$minutes:$seconds"
    }

    fun Int.pad(): String {
        return this.toString().padStart(2, '0')
    }
}


