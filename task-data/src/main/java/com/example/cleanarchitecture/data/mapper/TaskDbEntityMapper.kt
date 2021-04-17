package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.taskdomain.model.Task
import javax.inject.Inject

class TaskDbEntityMapper
@Inject
constructor(): Mapper<Task, TaskDbEntity>() {

    override fun reverse(input: Task) = TaskDbEntity(input.title)

    override fun map(input: TaskDbEntity) = Task(input.title)
}