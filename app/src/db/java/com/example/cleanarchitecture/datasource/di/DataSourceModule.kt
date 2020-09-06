package com.example.cleanarchitecture.datasource.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.core.TASK_DATABASE_NAME
import com.example.cleanarchitecture.datasource.persistence.LocalTaskDataSource
import com.example.cleanarchitecture.datasource.persistence.TaskDatabase
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import dagger.Module
import dagger.Provides

@Module
object DataSourceModule {

    @JvmStatic
    @Provides
    fun provideDatabase(applicationContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, TASK_DATABASE_NAME
        ).build()
    }

    @JvmStatic
    @Provides
    fun provideDataSource(
        database: TaskDatabase
    ): TaskDataSource =
        LocalTaskDataSource(database.taskDao())

}