package com.example.cleanarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.source.local.dao.TaskDao
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [TaskDbEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}