package com.example.cleanarchitecture.domain.repository


import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Access point for managing task data.
 */
interface TaskRepository {
    fun insertTaskLocally(task: Task): Completable

    fun getAllTasksLocally(): Single<List<Task>>

    fun insertTaskRemotely(task: Task): Completable

    fun getAllTasksRemotely(): Single<List<Task>>
}