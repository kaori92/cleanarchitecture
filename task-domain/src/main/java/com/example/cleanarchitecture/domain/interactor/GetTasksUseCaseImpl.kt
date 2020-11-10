package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Single

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : GetTasksUseCase {

    override fun execute(): Single<List<Task>> = try {
        taskRepository.getAllTasks(isOnline())
    } catch (exception: Exception) {
        Single.error(exception)
    }

    private fun isOnline() = connectivityChecker.isOnline()

}