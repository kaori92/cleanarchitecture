package com.example.cleanarchitecture.task.models

import com.example.cleanarchitecture.data.source.local.TaskLocalSource
import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Single

class DefaultTaskModel(
    private val taskDataSource: TaskLocalSource
// TODO add remote source
) : TaskModel {

    override fun getAllTasks(): Single<List<Task>> {
        return taskDataSource.getAllTasks()
    }
}