package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.domain.repository.NotificationRepository

class GetNotificationsUseCaseImpl(
    private val notificationRepository: NotificationRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetNotificationsUseCase {

    override fun execute() = notificationRepository.getAllNotifications(isOnline())

    private fun isOnline() = connectivityChecker.isOnline()
}