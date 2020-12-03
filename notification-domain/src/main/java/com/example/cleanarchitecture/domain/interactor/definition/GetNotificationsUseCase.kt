package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.MyNotification
import io.reactivex.Single

interface GetNotificationsUseCase {
    fun execute(): Single<List<MyNotification>>
}