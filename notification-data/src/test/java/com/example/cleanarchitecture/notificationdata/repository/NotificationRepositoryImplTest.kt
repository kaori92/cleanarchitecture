package com.example.cleanarchitecture.notificationdata.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.notificationdata.source.NotificationLocalSource
import com.example.cleanarchitecture.notificationdata.source.NotificationRemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.notificationdomain.model.MyNotification
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFailsWith

class NotificationRepositoryImplTest : Spek({

    val remoteSource by memoized { mock<NotificationRemoteSource>() }
    val localSource by memoized { mock<NotificationLocalSource>() }
    val timeService by memoized { mock<TimeService>() }

    val notificationRepository by memoized {
        NotificationRepositoryImpl(remoteSource, localSource, timeService)
    }

    val notification = MyNotification("abc")
    val notifications = listOf(MyNotification("abc"))

    lateinit var observer: TestObserver<Void>
    lateinit var testObserverList: TestObserver<List<MyNotification>>

    describe("inserting notification") {

        context("when online") {
            beforeEachTest {
                given(remoteSource.insertNotification(notification)).willReturn(Completable.complete())
                given(localSource.insertNotification(notification)).willReturn(Completable.complete())

                observer = notificationRepository.insertNotification(notification, true).test()
            }

            it("should call local source") {
                verify(localSource).insertNotification(notification)
            }

            it("should call remote source") {
                verify(remoteSource).insertNotification(notification)
            }

            it("should complete") {
                observer.assertComplete()
            }
        }

        context("when offline") {
            it("should return error") {
                assertFailsWith<NoInternetException> {
                    notificationRepository.insertNotification(notification, false)
                }
            }
        }
    }

    describe("getting notifications") {

        context("when online") {
            beforeEachTest {
                given(remoteSource.getAllNotifications()).willReturn(Single.just(notifications))
                testObserverList = notificationRepository.getAllNotifications(true).test()
            }

            it("should return notifications") {
                testObserverList.assertResult(notifications)
            }

            it("should remoteSource call getAllNotifications") {
                verify(remoteSource).getAllNotifications()
            }

            it("should timeService call updateCacheTimestampMs") {
                verify(timeService).updateCacheTimestampMs()
            }
        }

        context("when offline") {
            context("and cache limit passed") {
                beforeEachTest {
                    given(timeService.isTimeoutExceeded()).willReturn(true)
                }

                it("should return error") {
                    assertFailsWith<CachePassedException> {
                        notificationRepository.getAllNotifications(false)
                    }
                }
            }

            context("and cache limit NOT passed") {

                beforeEachTest {
                    given(timeService.isTimeoutExceeded()).willReturn(false)
                    given(localSource.getAllNotifications()).willReturn(Single.just(notifications))
                    testObserverList = notificationRepository.getAllNotifications(false).test()
                }

                it("should return notifications") {
                    testObserverList.assertResult(notifications)
                }

                it("should localSource call getAllNotifications") {
                    verify(localSource).getAllNotifications()
                }
            }
        }
    }

})