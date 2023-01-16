package com.example.habitsapp.di

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.habitsapp.R
import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "Habits Notifications ChannelId")
            .setContentTitle("Habit Started")
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.habits_app_icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(VISIBILITY_PUBLIC)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Habits Notifications ChannelId",
                "Habits Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

}












/*
@Module
@InstallIn(ServiceComponent::class)
object NotificationModule {


    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Habit")
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.habits_app_icon)
            .setOngoing(true)
            .addAction(0, "Stop", ServiceHelper.stopPendingIntent(context))
            .addAction(0, "Cancel", ServiceHelper.cancelPendingIntent(context))
            .setContentIntent(ServiceHelper.clickPendingIntent(context))
    }

    @ServiceScoped
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}*/
