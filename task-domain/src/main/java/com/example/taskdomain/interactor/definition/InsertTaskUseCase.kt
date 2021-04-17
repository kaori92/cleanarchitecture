package com.example.taskdomain.interactor.definition

import com.example.taskdomain.model.Task

interface InsertTaskUseCase {

    suspend fun execute(task: Task) : Unit
}