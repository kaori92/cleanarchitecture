package com.example.taskpresentation.viewmodel.addTask

sealed class AddTaskViewAction {
    object ShowSuccessMessage : AddTaskViewAction()
    data class ShowErrorMessage(val message: String) : AddTaskViewAction()
}