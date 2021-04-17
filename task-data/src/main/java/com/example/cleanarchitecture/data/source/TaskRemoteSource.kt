package com.example.cleanarchitecture.data.source

import com.example.taskdomain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

interface TaskRemoteSource {
    fun insertTask(task: Task): Completable
    suspend fun getAllTasks(): List<Task>
}