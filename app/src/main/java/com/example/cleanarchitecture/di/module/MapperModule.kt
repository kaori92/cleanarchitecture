package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.data.source.model.TaskEntity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    internal fun taskMapper(mapper: TaskMapper): Mapper<Task, TaskDTO> {
        return mapper
    }

    @Singleton
    @Provides
    internal fun taskEntityMapper(mapper: TaskEntityMapper): Mapper<Task, TaskEntity> {
        return mapper
    }
}