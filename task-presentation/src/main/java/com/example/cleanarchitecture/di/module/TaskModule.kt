package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.GetTasksUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
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
    ): GetTasksUseCase =
        GetTasksUseCaseImpl(
            taskRepositoryImpl,
            connectivityChecker
        )

    @Provides
    fun providePresenter(
        model: GetTasksUseCase
    ): TaskListPresenter =
        TaskListPresenter(
            model,
            AndroidSchedulerProvider
        )
}