package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.local.model.NotificationDbEntity
import com.example.cleanarchitecture.domain.model.MyNotification
import javax.inject.Inject

class NotificationDbEntityMapper
@Inject
constructor(): Mapper<MyNotification, NotificationDbEntity>() {

    override fun reverse(input: MyNotification) = NotificationDbEntity(input.title)

    override fun map(input: NotificationDbEntity) = MyNotification(input.title)
}