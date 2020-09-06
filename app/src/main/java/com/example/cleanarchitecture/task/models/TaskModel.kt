package com.example.cleanarchitecture.task.models

import com.example.cleanarchitecture.datasource.data.Task
import io.reactivex.Single

interface TaskModel {
    fun getAllTasks(): Single<Array<Task>>
}