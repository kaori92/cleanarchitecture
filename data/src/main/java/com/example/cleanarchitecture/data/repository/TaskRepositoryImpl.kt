package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl
@Inject
constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : TaskRepository {

    override fun insertTaskLocally(task: Task): Completable {
        return localSource.insertTask(task)
    }

    override fun getAllTasksLocally(): Single<List<Task>> {
        return localSource.getAllTasks()
    }

    override fun insertTaskRemotely(task: Task): Completable {
        return remoteSource.insertTask(task)
    }

    override fun getAllTasksRemotely(): Single<List<Task>> {
        return remoteSource.getAllTasks()
    }
}