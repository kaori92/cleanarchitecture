package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.time.TimeService
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModel
import java.lang.IllegalArgumentException

class TaskListViewModelFactory(
    private val getTasksUseCase: GetTasksUseCase,
    private val timeService: TimeService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            TaskListViewModel(
                getTasksUseCase,
                timeService
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
}