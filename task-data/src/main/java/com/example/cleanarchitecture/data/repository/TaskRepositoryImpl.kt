package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single
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

    override fun getAllTasks(isOnline: Boolean): Single<List<Task>> {
        return if (isOnline) {
            remoteSource
                .getAllTasks()
                .doOnSuccess {
                    timeService.updateCacheTimestampMs()
                }
        } else {
            if (timeService.getTime() - timeService.cacheTimestampMs > timeService.getCacheLimitMs()) {
                throw CachePassedException("Cache limit passed")
            } else {
                localSource.getAllTasks()
            }
        }
    }

}