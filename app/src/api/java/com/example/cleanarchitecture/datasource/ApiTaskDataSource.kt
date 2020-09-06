package com.example.cleanarchitecture.datasource

import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ApiTaskDataSource(
    private val taskRetrofitService: TaskRetrofitService
) : TaskDataSource {

    // will only show toast with message "Task added" because the mock API does not allow creating tasks
    override fun insertOrUpdateTask(task: Task): Completable {
        return taskRetrofitService.addTask(Task(task.title))
    }

    override fun getAllTasks(): Single<Array<Task>> {
        return taskRetrofitService.getTasks()
    }

}