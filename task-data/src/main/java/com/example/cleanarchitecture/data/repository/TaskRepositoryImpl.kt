package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository
import io.reactivex.Completable
import javax.inject.Inject


class TaskRepositoryImpl
@Inject
constructor(
    private val remoteSource: TaskRemoteSource,
    private val localSource: TaskLocalSource,
    private val timeService: TimeService
) : TaskRepository {

    override fun insertTask(task: Task, isOnline: Boolean): Completable {
        return if (isOnline) {
            remoteSource
                .insertTask(task)
                .andThen(localSource.insertTask(task))
        } else {
            throw NoInternetException("No internet - failed to insert task")
        }
    }

    override suspend fun getAllTasks(isOnline: Boolean): List<Task> {
        return if (isOnline) {
            remoteSource.getAllTasks()
        } else {
            if (timeService.isTimeoutExceeded()) {
                throw CachePassedException("Cache limit passed")
            } else {
                localSource.getAllTasks()
            }
        }
    }


}