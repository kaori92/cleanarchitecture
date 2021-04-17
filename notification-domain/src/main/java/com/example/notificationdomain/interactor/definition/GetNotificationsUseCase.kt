package com.example.notificationdomain.interactor.definition

import com.example.notificationdomain.model.MyNotification
import io.reactivex.Single

interface GetNotificationsUseCase {
    fun execute(): Single<List<MyNotification>>
}