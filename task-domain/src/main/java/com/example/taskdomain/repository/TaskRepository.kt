package com.example.taskdomain.repository

import com.example.taskdomain.model.Task
import io.reactivex.Completable

/**
 * Access point for managing task data.
 */
interface TaskRepository {
    fun insertTask(task: Task, isOnline: Boolean): Completable

    suspend fun getAllTasks(isOnline: Boolean): List<Task>
}