package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import com.example.cleanarchitecture.string.StringService
import io.reactivex.Completable

class DefaultAddTaskModel(
    private val taskRepository: TaskRepository,
    private val stringService: StringService,
    private val connectivityChecker: ConnectivityChecker
) : AddTaskModel {

    override fun insertTask(task: Task): Completable {
        return taskRepository.insertTask(task, isOnline())
    }

    override fun getStringResource(id: Int) = stringService.getStringResource(id)

    private fun isOnline() = connectivityChecker.isOnline()

}