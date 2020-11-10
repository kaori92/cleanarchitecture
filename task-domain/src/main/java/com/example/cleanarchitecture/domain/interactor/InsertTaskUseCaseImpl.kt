package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.InsertTaskUseCase
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable

class InsertTaskUseCaseImpl(
    private val taskRepository: TaskRepository,
    private val connectivityChecker: ConnectivityChecker
) : InsertTaskUseCase {

    override fun execute(task: Task): Completable = try {
        taskRepository.insertTask(task, isOnline())
    } catch (exception: Exception) {
        Completable.error(exception)
    }

    private fun isOnline() = connectivityChecker.isOnline()

}