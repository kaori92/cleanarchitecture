package com.example.taskpresentation.viewmodel.task

import com.example.taskdomain.model.Task

sealed class TaskListViewAction {
    object ShowLoading : TaskListViewAction()
    data class ShowTasks(val tasks: List<Task>) : TaskListViewAction()
    data class ShowErrorMessage(val message: String) : TaskListViewAction()
}
