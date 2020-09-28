package com.example.cleanarchitecture.data.source.local

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.local.dao.TaskDao
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskLocalSource
@Inject
constructor(
    private val taskDao: TaskDao,
    private val mapperDb: Mapper<Task, TaskDbEntity>
): LocalSource {

    override fun insertTask(task: Task): Completable {
        return taskDao.insertTask(mapperDb.reverse(task))
    }

    override fun getAllTasks(): Single<List<Task>> {
        return taskDao.getAllTasks().map {
            mapperDb.map(it)
        }
    }

}