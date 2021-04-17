package com.example.cleanarchitecture.notificationdata.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.notificationdata.source.local.model.NotificationDbEntity
import com.example.notificationdomain.model.MyNotification
import javax.inject.Inject

class NotificationDbEntityMapper
@Inject
constructor(): Mapper<MyNotification, NotificationDbEntity>() {

    override fun reverse(input: MyNotification) = NotificationDbEntity(input.title)

    override fun map(input: NotificationDbEntity) = MyNotification(input.title)
}