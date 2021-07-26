package com.example.notificationpresentation.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.notificationdata.repository.NotificationRepositoryImpl
import com.example.notificationdomain.interactor.GetNotificationsUseCaseImpl
import com.example.notificationdomain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.notificationpresentation.ui.notification.NotificationListPresenter
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