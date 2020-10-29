package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.DefaultAddTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.string.StringService
import dagger.Module
import dagger.Provides

@Module
object AddTaskModule {

    @JvmStatic
    @Provides
    fun provideModel(
        taskRepositoryImpl: TaskRepositoryImpl,
        stringService: StringService,
        connectivityChecker: ConnectivityChecker
    ): AddTaskModel =
        DefaultAddTaskModel(
            taskRepositoryImpl,
            stringService,
            connectivityChecker
        )

    @JvmStatic
    @Provides
    fun providePresenter(
        model: AddTaskModel
    ): AddTaskPresenter =
        AddTaskPresenter(
            model,
            AndroidSchedulerProvider
        )
}