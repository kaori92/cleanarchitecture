package com.example.taskpresentation.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.data.time.TimeService
import com.example.corepresentation.dispatcher.di.AndroidDispatcherProvider
import com.example.taskdomain.interactor.GetTasksUseCaseImpl
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskpresentation.viewmodel.task.TaskListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object TaskModule {

    @Provides
    internal fun provideGetTasksUseCase(
        taskRepositoryImpl: TaskRepositoryImpl,
        connectivityChecker: ConnectivityChecker,
        timeService: TimeService
    ): GetTasksUseCase =
        GetTasksUseCaseImpl(
            taskRepositoryImpl,
            connectivityChecker,
            timeService
        )

    @JvmStatic
    @Provides
    fun provideViewModelFactory(
        getTasksUseCase: GetTasksUseCase
    ): TaskListViewModelFactory =
        TaskListViewModelFactory(
            getTasksUseCase,
            AndroidDispatcherProvider
        )
}