package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.NOTIFICATIONS_FIELD_NAME
import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationRetrofitService {

    @GET(NOTIFICATIONS_FIELD_NAME)
    fun getNotifications(): Single<List<NotificationApiDto>>

    @POST(NOTIFICATIONS_FIELD_NAME)
    fun insertNotification(@Body notificationRequest: NotificationApiDto): Completable
}