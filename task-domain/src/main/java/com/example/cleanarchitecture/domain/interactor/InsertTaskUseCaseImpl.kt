package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.InsertTaskUseCase
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository

class InsertTaskUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : InsertTaskUseCase {

    override fun execute(task: Task) = taskRepository.insertTask(task, isOnline())

    private fun isOnline() = connectivityChecker.isOnline()

}