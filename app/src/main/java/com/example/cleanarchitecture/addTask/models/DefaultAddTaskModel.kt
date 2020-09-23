package com.example.cleanarchitecture.addTask.models

import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.data.source.TaskDataSource
import io.reactivex.Completable

class DefaultAddTaskModel(
    private val taskDataSource: TaskDataSource
) : AddTaskModel {

    override fun insertOrUpdateTask(task: Task): Completable {
        return taskDataSource.insertOrUpdateTask(task)
    }
}