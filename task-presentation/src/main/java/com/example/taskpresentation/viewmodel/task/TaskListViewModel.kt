package com.example.taskpresentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.example.cleanarchitecture.Resource
import com.example.cleanarchitecture.data.time.TimeService
import kotlinx.coroutines.Dispatchers

class TaskListViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val timeService: TimeService
) : ViewModel() {

    fun getAllTasks() =
        liveData<Resource<List<Task>>>(Dispatchers.IO) {
            emit(Resource.loading())
            try {
                emit(Resource.success(data = getTasksUseCase.execute()))
                timeService.updateCacheTimestampMs()
            } catch (exception: Exception) {
                emit(Resource.error(data = null, msg = "Error Occurred getting tasks: ${exception.message}"))
            }
        }
}