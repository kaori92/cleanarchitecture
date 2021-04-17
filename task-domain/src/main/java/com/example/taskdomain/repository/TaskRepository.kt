package com.example.taskdomain.repository

import com.example.taskdomain.model.Task
import io.reactivex.Completable

/**
 * Access point for managing task data.
 */
interface TaskRepository {
    suspend fun insertTask(task: Task, isOnline: Boolean)

    suspend fun getAllTasks(isOnline: Boolean): List<Task>
}