package com.example.taskpresentation.viewmodel.addTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.taskdomain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase
) : ViewModel() {

    val viewAction = MutableLiveData<AddTaskViewAction>()

    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertTaskUseCase.execute(task)
                viewAction.postValue(AddTaskViewAction.ShowSuccessMessage)
            } catch (exception: Exception) {
                viewAction.postValue(
                    AddTaskViewAction.ShowErrorMessage(
                        message = "Error occurred inserting task $task: ${exception.message}"
                    )
                )
            }
        }
    }

    fun getString(id: Int) = getStringUseCase.execute(id)
}