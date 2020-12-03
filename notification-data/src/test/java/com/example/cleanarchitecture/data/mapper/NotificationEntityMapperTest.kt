package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.source.local.model.NotificationDbEntity
import com.example.cleanarchitecture.domain.model.MyNotification
import org.junit.Assert
import org.junit.Test

class NotificationEntityMapperTest {

    private val mapper = NotificationDbEntityMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToNotification() {
        val notificationEntity = NotificationDbEntity(title)
        val notification = mapper.map(notificationEntity)
        Assert.assertEquals(title, notification.title)
    }

    @Test
    fun reverseTest_shouldMapToNotificationEntity() {
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
        val notification = NotificationDbEntity(title)
        val mappedList = mapper.map(listOf(notification, notification, notification, notification))
        Assert.assertEquals(4, mappedList.size)
        Assert.assertEquals(title, mappedList[0].title)
    }
}