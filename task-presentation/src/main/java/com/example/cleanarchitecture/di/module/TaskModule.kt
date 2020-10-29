package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.DefaultTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Module
import dagger.Provides

@Module
object TaskModule {

    @Provides
    internal fun provideModel(
        taskRepositoryImpl: TaskRepositoryImpl,
        connectivityChecker: ConnectivityChecker
    ): TaskModel =
        DefaultTaskModel(
            taskRepositoryImpl,
            connectivityChecker
        )

    @Provides
    fun providePresenter(
        model: TaskModel
    ): TaskListPresenter =
        TaskListPresenter(
            model,
            AndroidSchedulerProvider
        )
}