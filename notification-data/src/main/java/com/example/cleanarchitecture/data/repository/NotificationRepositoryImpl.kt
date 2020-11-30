package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.NotificationLocalSource
import com.example.cleanarchitecture.data.source.NotificationRemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.domain.repository.NotificationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class NotificationRepositoryImpl
@Inject
constructor(
    private val remoteSource: NotificationRemoteSource,
    private val localSource: NotificationLocalSource,
    private val timeService: TimeService
) : NotificationRepository {
    override fun insertNotification(notification: MyNotification, isOnline: Boolean): Completable {
        return if (isOnline) {
            remoteSource
                .insertNotification(notification)
                .andThen(localSource.insertNotification(notification))

        } else {
            throw NoInternetException("No internet - failed to insert notification")
        }
    }

    override fun getAllNotifications(isOnline: Boolean): Single<List<MyNotification>> {
        return if (isOnline) {
            remoteSource
                .getAllNotifications()
                .doOnSuccess {
                    timeService.updateCacheTimestampMs()
                }
        } else {
            if(timeService.isTimeoutExceeded()){
                throw CachePassedException("Cache limit passed")
            } else {
                localSource.getAllNotifications()
            }
        }
    }

}