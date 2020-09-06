package com.example.cleanarchitecture.datasource.persistence

import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import io.reactivex.Completable
import io.reactivex.Single

class LocalTaskDataSource(
    private val taskDao: TaskDao
) : TaskDataSource {

    override fun insertOrUpdateTask(task: Task): Completable {
        return taskDao.insertTask(task)
    }

    override fun getAllTasks(): Single<Array<Task>> {
        return taskDao.getAllTasks()
    }

}