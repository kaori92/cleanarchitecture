package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.MyNotification
import io.reactivex.Completable

interface InsertNotificationUseCase {

    fun execute(notification: MyNotification) : Completable
}