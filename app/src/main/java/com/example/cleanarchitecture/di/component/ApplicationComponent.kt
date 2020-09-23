package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.di.module.DataSourceModule
import com.example.cleanarchitecture.di.module.AppModule
import com.example.cleanarchitecture.di.module.StringServiceModule
import com.example.cleanarchitecture.di.module.TaskModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DataSourceModule::class,  TaskModule::class, StringServiceModule::class]
)
interface ApplicationComponent {

    fun getApplication(): MyApplication

    fun requestTaskComponentBuilder(): TaskComponent.Builder

    fun requestAddTaskComponentBuilder(): AddTaskComponent.Builder
}