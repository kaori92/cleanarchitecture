package com.example.cleanarchitecture.data.source

import com.example.taskdomain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

interface TaskLocalSource {
    fun insertTask(task: Task): Completable
    fun getAllTasks(): List<Task>
}