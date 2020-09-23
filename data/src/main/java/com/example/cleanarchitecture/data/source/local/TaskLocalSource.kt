package com.example.cleanarchitecture.data.source.local

import com.example.cleanarchitecture.data.mapper.TaskDbEntityMapper
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

class TaskLocalSource(
    private val db: TaskDatabase,
    private val mapperDb: TaskDbEntityMapper
): LocalSource {

    override fun insertOrUpdateTask(task: Task): Completable {
        return db.taskDao().insertTask(mapperDb.reverse(task))
    }

    override fun getAllTasks(): Single<List<Task>> {
        return db.taskDao().getAllTasks().map {
            mapperDb.map(it)
        }
    }

}