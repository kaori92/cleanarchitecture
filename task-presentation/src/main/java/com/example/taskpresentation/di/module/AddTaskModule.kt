package com.example.taskpresentation.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.taskdomain.interactor.GetStringResourceUseCaseImpl
import com.example.taskdomain.interactor.InsertTaskUseCaseImpl
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.string.StringService
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModel
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object AddTaskModule {

    @JvmStatic
    @Provides
    fun provideInsertTaskUseCase(
        taskRepositoryImpl: TaskRepositoryImpl,
        connectivityChecker: ConnectivityChecker
    ): InsertTaskUseCase =
        InsertTaskUseCaseImpl(
            taskRepositoryImpl,
            connectivityChecker
        )

    @JvmStatic
    @Provides
    fun provideGetStringResourceUseCase(
        stringService: StringService
    ): GetStringResourceUseCase =
        GetStringResourceUseCaseImpl(
            stringService
        )

    @JvmStatic
    @Provides
    fun provideViewModelFactory(
        insertTaskUseCase: InsertTaskUseCase,
        getStringUseCase: GetStringResourceUseCase
    ): AddTaskViewModelFactory =
        AddTaskViewModelFactory(
            insertTaskUseCase,
            getStringUseCase
        )
}