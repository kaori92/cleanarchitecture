package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single

class TaskRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : TaskRepository {

    override fun insertOrUpdateTaskLocally(task: Task): Completable {
        return localSource.insertOrUpdateTask(task)
    }

    override fun getAllTasksLocally(): Single<List<Task>> {
        return localSource.getAllTasks()
    }

    override fun insertOrUpdateTaskRemotely(task: Task): Completable {
        return remoteSource.insertOrUpdateTask(task)
    }

    override fun getAllTasksRemotely(): Single<List<Task>> {
        return remoteSource.getAllTasks()
    }
}