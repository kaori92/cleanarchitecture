package com.example.cleanarchitecture.task.di

import com.example.cleanarchitecture.core.AndroidSchedulerProvider
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import com.example.cleanarchitecture.task.models.DefaultTaskModel
import com.example.cleanarchitecture.task.models.TaskModel
import com.example.cleanarchitecture.task.presenter.TaskListPresenter
import dagger.Module
import dagger.Provides

@Module
object TaskModule {

    @JvmStatic
    @Provides
    fun provideModel(
        taskDataSource: TaskDataSource
    ): TaskModel =
        DefaultTaskModel(taskDataSource)

    @JvmStatic
    @Provides
    fun providePresenter(
        model: TaskModel
    ): TaskListPresenter =
        TaskListPresenter(model, AndroidSchedulerProvider)
}