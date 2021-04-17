package com.example.cleanarchitecture.notificationdata.source.local

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.notificationdata.source.NotificationLocalSource
import com.example.cleanarchitecture.notificationdata.source.local.dao.NotificationDao
import com.example.cleanarchitecture.notificationdata.source.local.model.NotificationDbEntity
import com.example.notificationdomain.model.MyNotification
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class NotificationLocalSourceTest : Spek({
    val title = "abc"
    val notification = MyNotification(title)
    val notificationDbEntity = NotificationDbEntity(title)
    val notifications = listOf(notification)
    val notificationDbEntities = listOf(notificationDbEntity)
    val notificationDbEntitiesSingle = Single.just(notificationDbEntities)

    val notificationDao: NotificationDao by memoized {
        mock<NotificationDao>()
    }
    val mapperDb: Mapper<MyNotification, NotificationDbEntity> by memoized {
        mock<Mapper<MyNotification, NotificationDbEntity>>()
    }

    val notificationLocalSource: NotificationLocalSource by memoized {
        DefaultNotificationLocalSource(notificationDao, mapperDb)
    }

    lateinit var testObserver: TestObserver<Void>
    lateinit var testListObserver: TestObserver<List<MyNotification>>
    val error = Throwable("error")

    describe("inserting notification") {
        context("when calling insert notification") {
            beforeEachTest {
                given(mapperDb.reverse(notification)).willReturn(notificationDbEntity)

                notificationLocalSource.insertNotification(notification)
            }

            it("should notificationDao be called with insertNotification") {
                verify(notificationDao).insertNotification(notificationDbEntity)
            }

            it("should mapperDb be called with reverse") {
                verify(mapperDb).reverse(notification)
            }
        }

        context("when inserting notification succeeds") {

            beforeEachTest {
                given(notificationDao.insertNotification(notificationDbEntity)).willReturn(
                    Completable.complete()
                )
                given(mapperDb.reverse(notification)).willReturn(notificationDbEntity)

                testObserver = notificationLocalSource.insertNotification(notification).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting notification fails") {

            beforeEachTest {
                given(notificationDao.insertNotification(notificationDbEntity)).willReturn(
                    Completable.error(error)
                )
                given(mapperDb.reverse(notification)).willReturn(notificationDbEntity)

                testObserver = notificationLocalSource.insertNotification(notification).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }

    describe("getting all notifications") {
        lateinit var testObserver: TestObserver<List<MyNotification>>

        context("calling get all notifications") {
            beforeEachTest {
                given(notificationDao.getAllNotifications()).willReturn(notificationDbEntitiesSingle)

                testObserver = notificationLocalSource.getAllNotifications().test()
            }

            it("should mapperDb be called with map") {
                verify(mapperDb).map(notificationDbEntities)
            }

            it("should notificationDao be called with getAllNotifications") {
                verify(notificationDao).getAllNotifications()
            }

            it("should complete") {
                testObserver.assertComplete()
            }
        }

        context("when getting all notifications succeeds") {

            beforeEachTest {
                given(notificationDao.getAllNotifications()).willReturn(
                    notificationDbEntitiesSingle
                )
                given(mapperDb.map(notificationDbEntities)).willReturn(notifications)

                testListObserver = notificationLocalSource.getAllNotifications().test()
            }

            it("should completable be completed") {
                testListObserver.assertComplete()
            }
        }

        context("when getting all notifications fails") {

            beforeEachTest {
                given(notificationDao.getAllNotifications()).willReturn(Single.error(error))
                given(mapperDb.map(notificationDbEntities)).willReturn(notifications)

                testListObserver = notificationLocalSource.getAllNotifications().test()
            }

            it("should return error") {
                testListObserver.assertError(error)
            }
        }
    }
})