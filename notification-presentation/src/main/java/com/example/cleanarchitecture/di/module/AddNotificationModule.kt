package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.NotificationRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.GetNotificationsUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.GetStringResourceUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.InsertNotificationUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.domain.interactor.definition.InsertNotificationUseCase
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.string.StringService
import com.example.cleanarchitecture.ui.addNotification.AddNotificationPresenter
import com.example.cleanarchitecture.ui.notification.NotificationListPresenter
import dagger.Module
import dagger.Provides

@Module
object AddNotificationModule {

    @Provides
    internal fun provideModel(
        notificationRepositoryImpl: NotificationRepositoryImpl,
        connectivityChecker: ConnectivityChecker
    ): InsertNotificationUseCase =
        InsertNotificationUseCaseImpl(
            notificationRepositoryImpl,
            connectivityChecker
        )

    @JvmStatic
    @Provides
    fun provideGetStringResourceUseCase(
        stringService: StringService
    ): GetStringResourceUseCase =
        GetStringResourceUseCaseImpl(
            stringService
        )

    @Provides
    fun providePresenter(
        useCase: InsertNotificationUseCase,
        getStringUseCase: GetStringResourceUseCase
    ): AddNotificationPresenter =
        AddNotificationPresenter(
            useCase,
            getStringUseCase,
            AndroidSchedulerProvider
        )
}