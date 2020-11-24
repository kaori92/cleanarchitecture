package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.data.source.di.TaskDataSourceModule
import com.example.cleanarchitecture.di.module.TaskModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Component

@Component(
    modules = [
        TaskModule::class,
        TaskDataSourceModule::class,
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
