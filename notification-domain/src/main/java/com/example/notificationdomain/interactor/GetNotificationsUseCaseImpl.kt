package com.example.notificationdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.notificationdomain.interactor.definition.GetNotificationsUseCase
import com.example.notificationdomain.repository.NotificationRepository

class GetNotificationsUseCaseImpl(
    private val notificationRepository: NotificationRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetNotificationsUseCase {

    override fun execute() = notificationRepository.getAllNotifications(isOnline())

    private fun isOnline() = connectivityChecker.isOnline()
}