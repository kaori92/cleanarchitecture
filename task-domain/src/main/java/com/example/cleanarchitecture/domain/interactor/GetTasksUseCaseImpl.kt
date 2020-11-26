package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.domain.repository.TaskRepository

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetTasksUseCase {

    override fun execute() = taskRepository.getAllTasks(isOnline())

    private fun isOnline() = connectivityChecker.isOnline()

}