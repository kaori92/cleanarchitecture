package com.example.cleanarchitecture.notificationdata.source.local

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.notificationdata.source.NotificationLocalSource
import com.example.cleanarchitecture.notificationdata.source.local.dao.NotificationDao
import com.example.cleanarchitecture.notificationdata.source.local.model.NotificationDbEntity
import com.example.notificationdomain.model.MyNotification
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DefaultNotificationLocalSource
@Inject
constructor(
    private val notificationDao: NotificationDao,
    private val mapperDb: Mapper<MyNotification, NotificationDbEntity>
): NotificationLocalSource {

    override fun insertNotification(notification: MyNotification): Completable {
        return notificationDao.insertNotification(mapperDb.reverse(notification))
    }

    override fun getAllNotifications(): Single<List<MyNotification>> {
        return notificationDao.getAllNotifications().map {
            mapperDb.map(it)
        }
    }

}