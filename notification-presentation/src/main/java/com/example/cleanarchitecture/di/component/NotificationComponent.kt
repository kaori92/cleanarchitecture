package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.data.source.di.NotificationDataSourceModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.module.NotificationModule
import com.example.cleanarchitecture.ui.notification.NotificationListPresenter
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