package com.example.cleanarchitecture.data.source

import com.example.taskdomain.model.Task

interface TaskLocalSource {
    fun insertTask(task: Task)
    fun getAllTasks(): List<Task>
}