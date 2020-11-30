package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import com.example.cleanarchitecture.domain.model.MyNotification
import org.junit.Assert
import org.junit.Test

class NotificationApiDtoMapperTest {

    private val mapper = NotificationApiDtoMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToNotification() {
        val notificationDto = NotificationApiDto(title)
        val notification = mapper.map(notificationDto)
        Assert.assertEquals(notification.title, title)
    }

    @Test
    fun reverseTest_shouldMapToNotificationApiDto() {
        val notification = MyNotification(title)
        val notificationDto = mapper.reverse(notification)
        Assert.assertEquals(notificationDto.title, title)
    }

    @Test
    fun mapTest_emptyList() {
        val mappedList = mapper.map(emptyList())
        Assert.assertEquals(mappedList.size, 0)
    }

    @Test
    fun mapTest_notEmptyList() {
        val notification = NotificationApiDto(title)
        val mappedList = mapper.map(listOf(notification, notification, notification, notification))
        Assert.assertEquals(mappedList.size, 4)
        Assert.assertEquals(mappedList[0].title, title)
    }

}