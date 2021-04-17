package com.example.notificationdomain.interactor.definition

import com.example.notificationdomain.model.MyNotification
import io.reactivex.Completable

interface InsertNotificationUseCase {

    fun execute(notification: MyNotification) : Completable
}