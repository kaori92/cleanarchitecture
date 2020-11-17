package com.example.cleanarchitecture.data.mapper.di

import com.example.cleanarchitecture.data.mapper.NotificationApiDtoMapper
import com.example.cleanarchitecture.data.mapper.NotificationDbEntityMapper
import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.local.model.NotificationDbEntity
import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import com.example.cleanarchitecture.domain.model.MyNotification
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    internal fun notificationApiDtoMapper(mapper: NotificationApiDtoMapper): Mapper<MyNotification, NotificationApiDto> {
        return mapper
    }

    @Provides
    internal fun notificationDbEntityMapper(mapper: NotificationDbEntityMapper): Mapper<MyNotification, NotificationDbEntity> {
        return mapper
    }
}