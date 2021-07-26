package com.example.taskpresentation.viewmodel.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.corepresentation.dispatcher.di.DispatcherProvider
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import java.lang.IllegalArgumentException

class AddTaskViewModelFactory(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            AddTaskViewModel(
                insertTaskUseCase,
                getStringUseCase,
                dispatcherProvider
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
}