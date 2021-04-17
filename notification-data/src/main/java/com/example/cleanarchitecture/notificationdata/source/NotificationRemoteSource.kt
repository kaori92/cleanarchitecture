package com.example.cleanarchitecture.notificationdata.source

import com.example.notificationdomain.model.MyNotification
import io.reactivex.Completable
import io.reactivex.Single

interface NotificationRemoteSource {
    fun insertNotification(notification: MyNotification): Completable
    fun getAllNotifications(): Single<List<MyNotification>>
}