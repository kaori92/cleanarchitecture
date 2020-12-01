package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.remote.model.NotificationApiDto
import com.example.cleanarchitecture.domain.model.MyNotification
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class NotificationRemoteSourceTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    lateinit var testListObserver: TestObserver<List<MyNotification>>

    val notificationRetrofitService by memoized {
        mock<NotificationRetrofitService>()
    }

    val mapperApi by memoized {
        mock<Mapper<MyNotification, NotificationApiDto>>()
    }

    val notificationRemoteSource by memoized {
        DefaultNotificationRemoteSource(notificationRetrofitService, mapperApi)
    }

    val title = "abc"
    val error = Throwable("error")
    val notification = MyNotification(title)
    val notificationApiDto = NotificationApiDto(title)
    val notificationApiDtos = listOf(notificationApiDto)
    val notifications = listOf(notification)
    val notificationListSingle = Single.just(notificationApiDtos)

    describe("inserting notification") {
        context("when calling insert notification"){
            beforeEachTest {
                given(mapperApi.reverse(notification)).willReturn(notificationApiDto)

                notificationRemoteSource.insertNotification(notification)
            }

            it("should notificationRetrofitService be called with insertNotification") {
                verify(notificationRetrofitService).insertNotification(notificationApiDto)
            }

            it("should mapperApi be called with reverse") {
                verify(mapperApi).reverse(notification)
            }
        }

        context("when inserting notification succeeds"){
            beforeEachTest {
                given(notificationRetrofitService.insertNotification(notificationApiDto)).willReturn(Completable.complete())
                given(mapperApi.reverse(notification)).willReturn(notificationApiDto)

                testObserver = notificationRemoteSource.insertNotification(notification).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting notification fails"){

            beforeEachTest {
                given(notificationRetrofitService.insertNotification(notificationApiDto)).willReturn(Completable.error(error))
                given(mapperApi.reverse(notification)).willReturn(notificationApiDto)

                testObserver = notificationRemoteSource.insertNotification(notification).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }

    describe("getting notifications") {

        context("getting all notifications"){
            beforeEachTest {
                given(notificationRetrofitService.getNotifications()).willReturn(notificationListSingle)
                notificationRemoteSource.getAllNotifications()
            }

            it("should notificationRetrofitService be called with getNotifications") {
                verify(notificationRetrofitService).getNotifications()
            }
        }

        context("getting all notifications"){
            beforeEachTest {
                given(notificationRetrofitService.getNotifications()).willReturn(notificationListSingle)

                notificationRemoteSource.getAllNotifications()
            }

            //TODO: fix
            it("should mapperApi be called with map") {
                verify(mapperApi).map(notificationApiDtos)
            }
        }

        context("when getting notifications succeeds"){

            beforeEachTest {
                given(notificationRetrofitService.getNotifications()).willReturn(notificationListSingle)
                given(mapperApi.map(notificationApiDtos)).willReturn(notifications)

                testListObserver = notificationRemoteSource.getAllNotifications().test()
            }

            it("should completable be completed") {
                testListObserver.assertComplete()
            }
        }

        context("when getting notifications fails"){

            beforeEachTest {
                given(notificationRetrofitService.getNotifications()).willReturn(Single.error(error))
                given(mapperApi.map(notificationApiDtos)).willReturn(notifications)

                testListObserver = notificationRemoteSource.getAllNotifications().test()
            }

            it("should return error") {
                testListObserver.assertError(error)
            }
        }
    }
})