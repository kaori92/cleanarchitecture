package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskApiDtoMapper
@Inject
constructor() : Mapper<Task, TaskApiDto>() {

    override fun map(input: TaskApiDto): Task {
        val title = input.title
        return Task(title)
    }

    override fun reverse(input: Task): TaskApiDto {
        return TaskApiDto(input.title)
    }
}
