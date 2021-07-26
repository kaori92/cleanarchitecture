package com.example.taskdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.time.TimeService
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker,
    private val timeService: TimeService
) : GetTasksUseCase {

    override suspend fun execute(): List<Task> {
        timeService.updateCacheTimestampMs()
        return taskRepository.getAllTasks(isOnline())
    }

    private fun isOnline() = connectivityChecker.isOnline()

}