package com.example.cleanarchitecture.data.source

import com.example.taskdomain.model.Task

interface TaskRemoteSource {
    suspend fun insertTask(task: Task)
    suspend fun getAllTasks(): List<Task>
}