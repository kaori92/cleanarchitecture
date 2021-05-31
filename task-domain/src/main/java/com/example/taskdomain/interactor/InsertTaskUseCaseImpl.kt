package com.example.taskdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository

class InsertTaskUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : InsertTaskUseCase {

    override suspend fun execute(task: Task) = taskRepository.insertTask(task, connectivityChecker.isOnline())

}