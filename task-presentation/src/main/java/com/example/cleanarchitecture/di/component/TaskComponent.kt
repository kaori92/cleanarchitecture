package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.data.source.di.DataSourceModule
import com.example.cleanarchitecture.di.module.TaskModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Component

@Component(
    modules = [
        TaskModule::class,
        DataSourceModule::class,
        ConnectivityModule::class,
        TimeServiceModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface TaskComponent {
    fun presenter(): TaskListPresenter

}
