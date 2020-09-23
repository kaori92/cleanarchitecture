package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.addTask.models.AddTaskModel
import com.example.cleanarchitecture.addTask.models.DefaultAddTaskModel
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
import com.example.cleanarchitecture.platform.AndroidSchedulerProvider
import com.example.cleanarchitecture.platform.StringService
import com.example.cleanarchitecture.data.source.local.TaskLocalSource
import dagger.Module
import dagger.Provides

@Module
object AddTaskModule {

    @JvmStatic
    @Provides
    fun provideModel(
        taskDataSource: TaskLocalSource // todo add remote
    ): AddTaskModel =
        DefaultAddTaskModel(taskDataSource)

    @JvmStatic
    @Provides
    fun providePresenter(
        model: AddTaskModel,
        stringService: StringService
    ): AddTaskPresenter =
        AddTaskPresenter(
            model,
            AndroidSchedulerProvider,
            stringService
        )
}