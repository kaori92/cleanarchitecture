package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.NotificationRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.GetNotificationsUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.ui.notification.NotificationListPresenter
import dagger.Module
import dagger.Provides

@Module
object NotificationModule {

    @Provides
    internal fun provideModel(
        notificationRepositoryImpl: NotificationRepositoryImpl,
        connectivityChecker: ConnectivityChecker
    ): GetNotificationsUseCase =
        GetNotificationsUseCaseImpl(
            notificationRepositoryImpl,
            connectivityChecker
        )

    @Provides
    fun providePresenter(
        model: GetNotificationsUseCase
    ): NotificationListPresenter =
        NotificationListPresenter(
            model,
            AndroidSchedulerProvider
        )
}