package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.domain.repository.NotificationRepository
import io.reactivex.Single

class GetNotificationsUseCaseImpl(
    private val notificationRepository: NotificationRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetNotificationsUseCase {

    override fun execute(): Single<List<MyNotification>> = try {
        notificationRepository.getAllNotifications(isOnline())
    } catch (exception: Exception) {
        Single.error(exception)
    }

    private fun isOnline() = connectivityChecker.isOnline()

}