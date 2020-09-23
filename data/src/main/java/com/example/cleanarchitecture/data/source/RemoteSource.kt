package com.example.cleanarchitecture.data.source

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

interface RemoteSource {
    fun insertOrUpdateTask(task: Task): Completable
    fun getAllTasks(): Single<List<Task>>
}