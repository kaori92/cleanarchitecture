package com.example.cleanarchitecture.addTask.models

import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import io.reactivex.Completable

class DefaultAddTaskModel(
    private val taskDataSource: TaskDataSource
) : AddTaskModel {

    override fun insertOrUpdateTask(task: Task): Completable {
        return taskDataSource.insertOrUpdateTask(task)
    }
}