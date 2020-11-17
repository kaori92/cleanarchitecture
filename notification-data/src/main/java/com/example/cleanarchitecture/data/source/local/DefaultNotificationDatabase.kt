package com.example.cleanarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.source.local.dao.NotificationDao
import com.example.cleanarchitecture.data.source.local.model.NotificationDbEntity

@Database(entities = [NotificationDbEntity::class], version = 1)
abstract class DefaultNotificationDatabase : RoomDatabase()
{
    abstract fun notificationDao(): NotificationDao
}