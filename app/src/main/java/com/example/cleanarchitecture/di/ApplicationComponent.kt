package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.addTask.di.AddTaskComponent
import com.example.cleanarchitecture.core.MyApplication
import com.example.cleanarchitecture.datasource.di.DataSourceModule
import com.example.cleanarchitecture.task.di.TaskComponent
import dagger.Component


@Component(
    modules = [AppModule::class, DataSourceModule::class, StringServiceModule::class]
)
interface ApplicationComponent {

    fun getApplication(): MyApplication

    fun requestTaskComponentBuilder(): TaskComponent.Builder

    fun requestAddTaskComponentBuilder(): AddTaskComponent.Builder
}