package com.example.notificationpresentation.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.notificationdata.source.di.NotificationDataSourceModule
import com.example.cleanarchitecture.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.notificationpresentation.di.module.AddNotificationModule
import com.example.cleanarchitecture.string.di.StringServiceModule
import com.example.notificationpresentation.ui.addNotification.AddNotificationPresenter
import dagger.Component

@Component(
    modules = [
        AddNotificationModule::class,
        NotificationDataSourceModule::class,
        ConnectivityModule::class,
        TimeServiceModule::class,
        StringServiceModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface AddNotificationComponent {
    fun presenter(): AddNotificationPresenter

}