package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.domain.model.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDbEntityMapper
@Inject
constructor(): Mapper<Task, TaskDbEntity>() {

    override fun reverse(input: Task): TaskDbEntity {

        val title = input.title

        return TaskDbEntity(title)
    }

    override fun map(input: TaskDbEntity): Task {
        return Task(input.title)
    }
}