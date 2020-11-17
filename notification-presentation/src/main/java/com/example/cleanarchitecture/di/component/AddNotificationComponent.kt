package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.data.source.di.DataSourceModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.module.AddNotificationModule
import com.example.cleanarchitecture.string.di.StringServiceModule
import com.example.cleanarchitecture.ui.addNotification.AddNotificationPresenter
import dagger.Component

@Component(
    modules = [
        AddNotificationModule::class,
        DataSourceModule::class,
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