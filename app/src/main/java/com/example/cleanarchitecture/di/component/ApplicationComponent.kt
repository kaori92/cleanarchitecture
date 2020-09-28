package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.local.TaskLocalSource
import com.example.cleanarchitecture.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        MapperModule::class,
        DataSourceModule::class,
        TaskModule::class,
        StringServiceModule::class
    ]
)
interface ApplicationComponent {

    fun getApplication(): MyApplication

    fun requestLocalSource(): LocalSource

    fun requestTaskComponentBuilder(): TaskComponent.Builder

    fun requestAddTaskComponentBuilder(): AddTaskComponent.Builder
}