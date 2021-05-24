package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.taskdomain.model.Task
import javax.inject.Inject

class DefaultTaskRemoteSource
@Inject
constructor(
    private val taskRetrofitService: TaskRetrofitService,
    private val mapperApi: Mapper<Task, TaskApiDto>
) : TaskRemoteSource {

    // will only show toast with message "Task added" because the mock API does not allow creating tasks
    override suspend fun insertTask(task: Task) =
        taskRetrofitService.insertTask(mapperApi.reverse(task))

    override suspend fun getAllTasks(): List<Task> {
        return taskRetrofitService.getTasks().map {
            mapperApi.map(it)
        }
    }
}