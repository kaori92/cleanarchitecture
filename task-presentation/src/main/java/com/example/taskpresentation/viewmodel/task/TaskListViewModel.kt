package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.*
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.corepresentation.dispatcher.di.DispatcherProvider
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val viewAction = MutableLiveData<TaskListViewAction>()
    fun getViewAction() = viewAction

    fun loadAllTasks() {
        viewModelScope.launch(dispatcherProvider.io()) {
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