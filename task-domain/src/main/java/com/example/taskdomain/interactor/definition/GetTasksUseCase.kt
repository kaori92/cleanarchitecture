package com.example.taskdomain.interactor.definition

import com.example.taskdomain.model.Task

interface GetTasksUseCase {
    suspend fun execute(): List<Task>
}