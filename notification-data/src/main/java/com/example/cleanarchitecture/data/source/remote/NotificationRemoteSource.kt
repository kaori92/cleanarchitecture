package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import com.example.cleanarchitecture.domain.model.MyNotification
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NotificationRemoteSource
@Inject
constructor(
    private val notificationRetrofitService: NotificationRetrofitService,
    private val mapperApi: Mapper<MyNotification, NotificationApiDto>
) : RemoteSource {

    // will only show toast with message "Notification added" because the mock API does not allow creating notifications
    override fun insertNotification(notification: MyNotification): Completable {
        return notificationRetrofitService.insertNotification(mapperApi.reverse(MyNotification(notification.title)))
    }

    override fun getAllNotifications(): Single<List<MyNotification>> {
        return notificationRetrofitService.getNotifications().map {
            mapperApi.map(it)
        }
    }
}