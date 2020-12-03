package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import com.example.cleanarchitecture.domain.model.MyNotification
import javax.inject.Inject

class NotificationApiDtoMapper
@Inject
constructor() : Mapper<MyNotification, NotificationApiDto>() {

    override fun map(input: NotificationApiDto) = MyNotification(input.title)

    override fun reverse(input: MyNotification) = NotificationApiDto(input.title)
}
