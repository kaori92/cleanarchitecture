package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.InsertNotificationUseCase
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.domain.repository.NotificationRepository
import io.reactivex.Completable

class InsertNotificationUseCaseImpl(
    private val notificationRepository: NotificationRepository,
    private val connectivityChecker: ConnectivityChecker
) : InsertNotificationUseCase {

    override fun execute(notification: MyNotification) = notificationRepository.insertNotification(notification, isOnline())

    private fun isOnline() = connectivityChecker.isOnline()

}