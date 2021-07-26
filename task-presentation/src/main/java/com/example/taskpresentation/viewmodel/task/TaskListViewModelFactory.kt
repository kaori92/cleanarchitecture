package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.corepresentation.dispatcher.di.DispatcherProvider
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import java.lang.IllegalArgumentException

class TaskListViewModelFactory(
    private val getTasksUseCase: GetTasksUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            TaskListViewModel(
                getTasksUseCase,
                dispatcherProvider
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
}