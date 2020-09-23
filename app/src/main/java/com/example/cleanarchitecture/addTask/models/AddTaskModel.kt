package com.example.cleanarchitecture.addTask.models

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable

interface AddTaskModel {

    fun insertOrUpdateTask(task: Task): Completable
}