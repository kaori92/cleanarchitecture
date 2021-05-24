package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.time.TimeService
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import java.lang.IllegalArgumentException

class TaskListViewModelFactory(
    private val getTasksUseCase: GetTasksUseCase,
    private val timeService: TimeService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            TaskListViewModel(
                getTasksUseCase,
                timeService
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
}