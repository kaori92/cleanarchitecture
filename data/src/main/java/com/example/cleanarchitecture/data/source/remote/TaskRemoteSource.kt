package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.mapper.TaskApiDtoMapper
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

class TaskRemoteSource(
    private val taskRetrofitService: TaskRetrofitService,
    private val mapperDb: TaskApiDtoMapper
) : RemoteSource {

    // will only show toast with message "Task added" because the mock API does not allow creating tasks
    override fun insertOrUpdateTask(task: Task): Completable {
        return taskRetrofitService.addTask(mapperDb.reverse(Task(task.title)))
    }

    override fun getAllTasks(): Single<List<Task>> {
        return taskRetrofitService.getTasks().map {
            mapperDb.map(it)
        }
    }
}