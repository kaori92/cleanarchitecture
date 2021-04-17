package com.example.taskpresentation.viewmodel.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.taskdomain.model.Task
import com.example.cleanarchitecture.Resource
import io.reactivex.Completable
import kotlinx.coroutines.Dispatchers

class AddTaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase
) : ViewModel() {

    fun insertTask(task: Task) =
        liveData<Resource<Completable>>(Dispatchers.IO) {
            emit(Resource.loading())

            try {
                insertTaskUseCase.execute(task)
                emit(Resource.success(null))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, msg = "Error Occurred inserting task $task: ${exception.message}"))
            }
        }

    fun getString(id: Int) = getStringUseCase.execute(id)
}