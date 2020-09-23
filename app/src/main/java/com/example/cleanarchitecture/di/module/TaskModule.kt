package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.data.source.local.TaskLocalSource
import com.example.cleanarchitecture.platform.AndroidSchedulerProvider
import com.example.cleanarchitecture.task.models.DefaultTaskModel
import com.example.cleanarchitecture.task.models.TaskModel
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TaskModule {

    @Singleton
    @Provides
    internal fun provideModel(
        taskDataSource: TaskLocalSource
    ): TaskModel =
        DefaultTaskModel(taskDataSource)

    @Singleton
    @Provides
    fun providePresenter(
        model: TaskModel
    ): TaskListPresenter =
        TaskListPresenter(
            model,
            AndroidSchedulerProvider
        )
}