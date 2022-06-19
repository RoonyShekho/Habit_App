package com.example.habitsapp.di

import android.app.Application
import androidx.room.Room
import com.example.habitsapp.data.repository.Repository
import com.example.habitsapp.data.database.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): HabitDatabase {
        return Room.databaseBuilder(app, HabitDatabase::class.java, "habit_db")
            .build()
    }


    @Provides
    @Singleton
    fun provideRepository(habitDatabase: HabitDatabase): Repository {
        return Repository(habitDatabase.habitDao)
    }
}