package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.domain.repository.NotificationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class NotificationRepositoryImpl
@Inject
constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val timeService: TimeService
) : NotificationRepository {

    override fun insertNotification(notification: MyNotification, isOnline: Boolean): Completable {
        return if (isOnline) {
            remoteSource.insertNotification(notification)
            localSource.insertNotification(notification)
        } else {
            throw NoInternetException("No internet - failed to insert notification")
        }
    }

    override fun getAllNotifications(isOnline: Boolean): Single<List<MyNotification>> {
        return if (isOnline) {
            timeService.setCacheTimestampMs(timeService.getTime())
            remoteSource.getAllNotifications()
        } else {
            if (timeService.getTime() - timeService.getCacheTimestampMs() > timeService.getCacheLimitMs()) {
                throw CachePassedException("Cache limit passed")
            } else {
                timeService.setCacheTimestampMs(timeService.getTime())
                localSource.getAllNotifications()
            }
        }
    }

}