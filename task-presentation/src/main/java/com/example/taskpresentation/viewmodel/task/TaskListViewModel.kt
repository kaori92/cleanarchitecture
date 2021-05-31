package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.*
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.data.time.TimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val viewAction = MutableLiveData<TaskListViewAction>()
    fun getViewAction() = viewAction

    fun loadAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            viewAction.postValue(TaskListViewAction.ShowLoading)
            try {
                viewAction.postValue(TaskListViewAction.ShowTasks(tasks = getTasksUseCase.execute()))
            } catch (exception: Exception) {
                viewAction.postValue(
                    TaskListViewAction.ShowErrorMessage(
                        message = "Error Occurred getting tasks: ${exception.message}"
                    )
                )
            }
        }
    }
}