package com.example.cleanarchitecture.notificationdata.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.notificationdata.source.local.dao.NotificationDao
import com.example.cleanarchitecture.notificationdata.source.local.model.NotificationDbEntity

@Database(entities = [NotificationDbEntity::class], version = 1)
abstract class DefaultNotificationDatabase : RoomDatabase()
{
    abstract fun notificationDao(): NotificationDao
}