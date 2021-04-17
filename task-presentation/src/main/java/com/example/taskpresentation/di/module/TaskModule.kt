package com.example.taskpresentation.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.data.time.TimeService
import com.example.taskdomain.interactor.GetTasksUseCaseImpl
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskpresentation.viewmodel.task.TaskListViewModel
import com.example.taskpresentation.viewmodel.task.TaskListViewModelFactory
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

    @JvmStatic
    @Provides
    fun provideViewModel(
        getTasksUseCase: GetTasksUseCase,
        timeService: TimeService
    ): TaskListViewModel =
        TaskListViewModel(
            getTasksUseCase,
            timeService
        )

    @JvmStatic
    @Provides
    fun provideViewModelFactory(
        getTasksUseCase: GetTasksUseCase,
        timeService: TimeService
    ): TaskListViewModelFactory =
        TaskListViewModelFactory(
            getTasksUseCase,
            timeService
        )
}