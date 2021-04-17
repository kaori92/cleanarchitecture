package com.example.taskpresentation.viewmodel.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import java.lang.IllegalArgumentException

class AddTaskViewModelFactory(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            AddTaskViewModel(
                insertTaskUseCase,
                getStringUseCase
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
}