package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import javax.inject.Inject

class TaskApiDtoMapper
@Inject
constructor() : Mapper<Task, TaskApiDto>() {

    override fun map(input: TaskApiDto) = Task(input.title)

    override fun reverse(input: Task) = TaskApiDto(input.title)
}
