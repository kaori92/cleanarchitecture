package com.example.cleanarchitecture.data.source

import com.example.cleanarchitecture.domain.model.MyNotification
import io.reactivex.Completable
import io.reactivex.Single

interface LocalSource {
    fun insertNotification(notification: MyNotification): Completable
    fun getAllNotifications(): Single<List<MyNotification>>
}