package com.example.notificationpresentation.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.notificationdata.repository.NotificationRepositoryImpl
import com.example.notificationdomain.interactor.GetStringResourceNotificationUseCaseImpl
import com.example.notificationdomain.interactor.InsertNotificationUseCaseImpl
import com.example.notificationdomain.interactor.definition.GetStringResourceNotificationUseCase
import com.example.notificationdomain.interactor.definition.InsertNotificationUseCase
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.string.StringService
import com.example.notificationpresentation.ui.addNotification.AddNotificationPresenter
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
    ): GetStringResourceNotificationUseCase =
        GetStringResourceNotificationUseCaseImpl(
            stringService
        )

    @Provides
    fun providePresenter(
        useCase: InsertNotificationUseCase,
        getStringUseCase: GetStringResourceNotificationUseCase
    ): AddNotificationPresenter =
        AddNotificationPresenter(
            useCase,
            getStringUseCase,
            AndroidSchedulerProvider
        )
}