package com.example.notificationdomain.repository


import com.example.notificationdomain.model.MyNotification
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Access point for managing notification data.
 */
interface NotificationRepository {
    fun insertNotification(notification: MyNotification, isOnline: Boolean): Completable

    fun getAllNotifications(isOnline: Boolean): Single<List<MyNotification>>
}