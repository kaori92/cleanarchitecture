package com.example.cleanarchitecture.data.mapper.di

import com.example.cleanarchitecture.data.mapper.TaskApiDtoMapper
import com.example.cleanarchitecture.data.mapper.TaskDbEntityMapper
import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.cleanarchitecture.domain.model.Task
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    internal fun taskApiDtoMapper(mapper: TaskApiDtoMapper): Mapper<Task, TaskApiDto> {
        return mapper
    }

    @Provides
    internal fun taskDbEntityMapper(mapper: TaskDbEntityMapper): Mapper<Task, TaskDbEntity> {
        return mapper
    }
}