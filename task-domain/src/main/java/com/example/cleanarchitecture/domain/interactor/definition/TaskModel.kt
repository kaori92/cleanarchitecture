package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Single

interface TaskModel {
    fun getAllTasks(isOnline: Boolean): Single<List<Task>>
}