package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable

class DefaultAddTaskModel(
    private val taskRepository: TaskRepository
) : AddTaskModel {

    override fun insertTaskRemotely(task: Task): Completable {
        return taskRepository.insertTaskRemotely(task)
    }

    override fun insertTaskLocally(task: Task): Completable {
        return taskRepository.insertTaskLocally(task)
    }
}