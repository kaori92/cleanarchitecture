package com.example.cleanarchitecture.di.module

import android.content.Context
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
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskModel =
        DefaultTaskModel(taskRepositoryImpl)

    @Provides
    fun providePresenter(
        model: TaskModel,
        connectivityChecker: ConnectivityChecker,
        context: Context
    ): TaskListPresenter =
        TaskListPresenter(
            model,
            AndroidSchedulerProvider,
            connectivityChecker,
            context
        )
}