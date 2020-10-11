package com.example.cleanarchitecture.data.repository

import android.os.SystemClock
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class TaskRepositoryImpl
@Inject
constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : TaskRepository {

    private val cacheLimit = 10 // smaller value for testing
    private var endTime: Long = 0
    private var elapsedMilliSeconds: Long = 0
    private var elapsedSeconds: Long = 0

    override fun insertTask(task: Task, isOnline: Boolean): Completable {
        val startTime: Long = SystemClock.elapsedRealtime()

        return if (isOnline) {
            remoteSource.insertTask(task)
            localSource.insertTask(task)
        } else {
            endTime = SystemClock.elapsedRealtime()
            elapsedMilliSeconds = endTime - startTime
            elapsedSeconds = elapsedMilliSeconds / 1000

            handleCache(startTime)

            localSource.insertTask(task)
        }
    }

    override fun getAllTasks(isOnline: Boolean): Single<List<Task>> {
        val startTime: Long = SystemClock.elapsedRealtime()

        return if (isOnline) {
            remoteSource.getAllTasks()
        } else {
            endTime = SystemClock.elapsedRealtime()
            elapsedMilliSeconds = endTime - startTime
            elapsedSeconds = elapsedMilliSeconds / 1000

            handleCache(startTime)

            localSource.getAllTasks()
        }
    }

    private fun handleCache(startTime: Long){
        while (elapsedSeconds < cacheLimit) {
            endTime = SystemClock.elapsedRealtime()
            elapsedMilliSeconds = endTime - startTime
            elapsedSeconds = elapsedMilliSeconds / 1000
        }
    }

}