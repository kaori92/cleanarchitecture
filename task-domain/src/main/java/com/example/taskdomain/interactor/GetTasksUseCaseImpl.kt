package com.example.taskdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.repository.TaskRepository

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetTasksUseCase {

    override suspend fun execute() = taskRepository.getAllTasks(isOnline())

    private fun isOnline() = connectivityChecker.isOnline()

}