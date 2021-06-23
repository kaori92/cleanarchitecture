package com.example.taskpresentation.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.data.source.di.TaskDataSourceModule
import com.example.taskpresentation.di.module.TaskModule
import com.example.cleanarchitecture.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.taskpresentation.viewmodel.task.TaskListViewModelFactory
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
    fun viewModelFactory(): TaskListViewModelFactory
}
