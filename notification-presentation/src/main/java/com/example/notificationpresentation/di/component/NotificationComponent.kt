package com.example.notificationpresentation.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.notificationdata.source.di.NotificationDataSourceModule
import com.example.cleanarchitecture.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.notificationpresentation.di.module.NotificationModule
import com.example.notificationpresentation.ui.notification.NotificationListPresenter
import dagger.Component

@Component(
    modules = [
        NotificationModule::class,
        NotificationDataSourceModule::class,
        ConnectivityModule::class,
        TimeServiceModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface NotificationComponent {
    fun presenter(): NotificationListPresenter

}