package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.di.module.AddTaskModule
import com.example.cleanarchitecture.data.source.di.TaskDataSourceModule
import com.example.cleanarchitecture.string.di.StringServiceModule
import com.example.cleanarchitecture.data.time.di.TimeServiceModule
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
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
    fun presenter(): AddTaskPresenter
}
