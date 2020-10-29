package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Single

class DefaultTaskModel(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : TaskModel {

    override fun getAllTasks(): Single<List<Task>> {
        return taskRepository.getAllTasks(isOnline())
    }

    private fun isOnline() = connectivityChecker.isOnline()
}