package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Single

class DefaultTaskModel(
    private val taskRepository: TaskRepository
) : TaskModel {

    override fun getAllTasksLocally(): Single<List<Task>> {
        return taskRepository.getAllTasksLocally()
    }

    override fun getAllTasksRemotely(): Single<List<Task>> {
        return taskRepository.getAllTasksRemotely()
    }
}