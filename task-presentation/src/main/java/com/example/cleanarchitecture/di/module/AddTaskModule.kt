package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.repository.TaskRepositoryImpl
import com.example.cleanarchitecture.domain.interactor.GetStringResourceUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.InsertTaskUseCaseImpl
import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.domain.interactor.definition.InsertTaskUseCase
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
import com.example.cleanarchitecture.scheduler.AndroidSchedulerProvider
import com.example.cleanarchitecture.string.StringService
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
    fun providePresenter(
        insertTaskUseCase: InsertTaskUseCase,
        getStringUseCase: GetStringResourceUseCase
    ): AddTaskPresenter =
        AddTaskPresenter(
            insertTaskUseCase,
            getStringUseCase,
            AndroidSchedulerProvider
        )
}