package com.example.cleanarchitecture.notificationdata.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.notificationdata.source.remote.model.NotificationApiDto
import com.example.notificationdomain.model.MyNotification
import javax.inject.Inject

class NotificationApiDtoMapper
@Inject
constructor() : Mapper<MyNotification, NotificationApiDto>() {

    override fun map(input: NotificationApiDto) = MyNotification(input.title)

    override fun reverse(input: MyNotification) = NotificationApiDto(input.title)
}
