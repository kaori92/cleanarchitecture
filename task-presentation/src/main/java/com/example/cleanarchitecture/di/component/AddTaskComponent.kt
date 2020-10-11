package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.connectivity.di.ConnectivityModule
import com.example.cleanarchitecture.di.module.AddTaskModule
import com.example.cleanarchitecture.di.module.DataSourceModule
import com.example.cleanarchitecture.string.di.StringServiceModule
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
import dagger.Component

@Component(
    modules = [
        AddTaskModule::class,
        StringServiceModule::class,
        DataSourceModule::class,
        ConnectivityModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface AddTaskComponent {
    fun presenter(): AddTaskPresenter
}
