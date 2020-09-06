package com.example.cleanarchitecture.datasource.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.datasource.data.Task
import javax.inject.Singleton

@Singleton
@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}