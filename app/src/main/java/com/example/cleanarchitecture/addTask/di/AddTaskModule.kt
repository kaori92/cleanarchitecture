package com.example.cleanarchitecture.addTask.di

import com.example.cleanarchitecture.addTask.models.AddTaskModel
import com.example.cleanarchitecture.addTask.models.DefaultAddTaskModel
import com.example.cleanarchitecture.addTask.presenter.AddTaskPresenter
import com.example.cleanarchitecture.core.AndroidSchedulerProvider
import com.example.cleanarchitecture.core.StringService
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import dagger.Module
import dagger.Provides

@Module
object AddTaskModule {

    @JvmStatic
    @Provides
    fun provideModel(
        taskDataSource: TaskDataSource
    ): AddTaskModel =
        DefaultAddTaskModel(taskDataSource)

    @JvmStatic
    @Provides
    fun providePresenter(
        model: AddTaskModel,
        stringService: StringService
    ): AddTaskPresenter =
        AddTaskPresenter(model, AndroidSchedulerProvider, stringService)
}