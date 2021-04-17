package com.example.notificationdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.notificationdomain.interactor.GetNotificationsUseCaseImpl
import com.example.notificationdomain.interactor.definition.GetNotificationsUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.notificationdomain.repository.NotificationRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetNotificationsUseCaseTest : Spek({
    val notificationRepository: NotificationRepository by memoized { mock<NotificationRepository>() }
    val connectivityChecker: ConnectivityChecker by memoized { mock<ConnectivityChecker>() }

    val model: GetNotificationsUseCase by memoized {
        GetNotificationsUseCaseImpl(
            notificationRepository,
            connectivityChecker
        )
    }

    val notifications = listOf(MyNotification("x"))
    val error = Throwable("error")
    val isOnline = true

    describe("get all notifications") {
        lateinit var testObserver: TestObserver<List<MyNotification>>

        context("when getting all notifications succeeds") {

            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(notificationRepository.getAllNotifications(isOnline)).willReturn(Single.just(notifications))

                testObserver = model.execute().test()
            }


            it("should complete") {
                testObserver.assertComplete()
            }

            it("should return correct notifications") {
                testObserver.assertResult(notifications)
            }
        }

        context("when getting all notifications failed") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(notificationRepository.getAllNotifications(isOnline)).willReturn(Single.error(error))

                testObserver = model.execute().test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})