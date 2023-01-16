package com.example.habitsapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.habitsapp.R
import com.example.habitsapp.ui.presentation.screen.start.StartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class CountDownService(
    val context:Context
) : Service() {


    private val vm =
        ViewModelProvider(ViewModelStore(), viewModelFactory { })[StartViewModel::class.java]

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "habit")
            .setContentTitle("Habit started")
            .setContentText("Time: ${vm.time}")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        startForeground(1, notification.build())
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }


    companion object {
        const val ACTION_START = "START_SERVICE"
        const val ACTION_STOP = "STOP_SERVICE"
    }
}