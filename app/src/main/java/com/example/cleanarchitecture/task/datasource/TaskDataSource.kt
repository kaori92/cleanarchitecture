package com.example.cleanarchitecture.task.datasource

import com.example.cleanarchitecture.datasource.data.Task
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Access point for managing task data.
 */
interface TaskDataSource {
    fun insertOrUpdateTask(task: Task): Completable

    fun getAllTasks(): Single<Array<Task>>
}