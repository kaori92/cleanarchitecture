package com.example.cleanarchitecture.task.models

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Single

interface TaskModel {
    fun getAllTasks(): Single<List<Task>>
}