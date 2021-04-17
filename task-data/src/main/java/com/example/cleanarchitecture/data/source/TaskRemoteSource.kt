package com.example.cleanarchitecture.data.source

import com.example.taskdomain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

interface TaskRemoteSource {
    fun insertTask(task: Task): Any
    suspend fun getAllTasks(): List<Task>
}