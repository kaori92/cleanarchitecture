package com.example.cleanarchitecture.domain.repository


import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Access point for managing task data.
 */
interface TaskRepository {
    fun insertTask(task: Task, isOnline: Boolean): Completable

    fun getAllTasks(isOnline: Boolean): Single<List<Task>>
}