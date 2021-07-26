package com.example.notificationdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.notificationdomain.interactor.definition.InsertNotificationUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.notificationdomain.repository.NotificationRepository

class InsertNotificationUseCaseImpl(
    private val notificationRepository: NotificationRepository,
    private val connectivityChecker: ConnectivityChecker
) : InsertNotificationUseCase {

    override fun execute(notification: MyNotification) = notificationRepository.insertNotification(notification, isOnline())

    private fun isOnline() = connectivityChecker.isOnline()

}