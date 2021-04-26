package com.example.taskpresentation.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.taskpresentation.di.module.AddTaskModule
import com.example.cleanarchitecture.data.source.di.TaskDataSourceModule
import com.example.cleanarchitecture.string.di.StringServiceModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModelFactory
import dagger.Component

@Component(
    modules = [
        AddTaskModule::class,
        StringServiceModule::class,
        ConnectivityModule::class,
        TimeServiceModule::class,
        TaskDataSourceModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface AddTaskComponent {
    fun factory(): AddTaskViewModelFactory
}
