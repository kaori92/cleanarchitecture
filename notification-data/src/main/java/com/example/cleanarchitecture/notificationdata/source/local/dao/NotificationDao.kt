package com.example.cleanarchitecture.notificationdata.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.notificationdata.source.local.model.NotificationDbEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NotificationDao {

    @Query("SELECT * FROM NotificationDbEntity")
    fun getAllNotifications(): Single<List<NotificationDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: NotificationDbEntity): Completable
}