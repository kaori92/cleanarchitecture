package com.example.cleanarchitecture.addTask.models

import com.example.cleanarchitecture.datasource.data.Task
import io.reactivex.Completable

interface AddTaskModel {

    fun insertOrUpdateTask(task: Task): Completable
}