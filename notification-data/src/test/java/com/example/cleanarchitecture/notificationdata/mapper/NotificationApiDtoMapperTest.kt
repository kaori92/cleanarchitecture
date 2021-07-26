package com.example.cleanarchitecture.notificationdata.mapper

import com.example.cleanarchitecture.notificationdata.source.remote.model.NotificationApiDto
import com.example.notificationdomain.model.MyNotification
import org.junit.Assert
import org.junit.Test

class NotificationApiDtoMapperTest {

    private val mapper = NotificationApiDtoMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToNotification() {
        val notificationDto = NotificationApiDto(title)
        val notification = mapper.map(notificationDto)
        Assert.assertEquals(title, notification.title)
    }

    @Test
    fun reverseTest_shouldMapToNotificationApiDto() {
        val notification = MyNotification(title)
        val notificationDto = mapper.reverse(notification)
        Assert.assertEquals(title, notificationDto.title)
    }

    @Test
    fun mapTest_emptyList() {
        val mappedList = mapper.map(emptyList())
        Assert.assertEquals(0, mappedList.size)
    }

    @Test
    fun mapTest_notEmptyList() {
        val notification = NotificationApiDto(title)
        val mappedList = mapper.map(listOf(notification, notification, notification, notification))
        Assert.assertEquals(4, mappedList.size)
        Assert.assertEquals(title, mappedList[0].title)
    }

}