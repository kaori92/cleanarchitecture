package com.example.cleanarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.source.local.dao.TaskDao
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity

@Database(entities = [TaskDbEntity::class], version = 1)
abstract class DefaultTaskDatabase : RoomDatabase()
{
    abstract fun taskDao(): TaskDao
}