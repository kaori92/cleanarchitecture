package com.example.cleanarchitecture.task.models

import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import io.reactivex.Single

class DefaultTaskModel(
    private val taskDataSource: TaskDataSource
) : TaskModel {

    override fun getAllTasks(): Single<Array<Task>> {
        return taskDataSource.getAllTasks()
    }
}