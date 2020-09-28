package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.DefaultTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.platform.AndroidSchedulerProvider
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TaskModule {

    @Singleton
    @Provides
    internal fun provideModel(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskModel =
        DefaultTaskModel(taskRepositoryImpl)

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