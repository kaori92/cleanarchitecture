package com.example.notificationdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.notificationdomain.interactor.InsertNotificationUseCaseImpl
import com.example.notificationdomain.interactor.definition.InsertNotificationUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.notificationdomain.repository.NotificationRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class InsertNotificationUseCaseTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    val notificationRepository: NotificationRepository by memoized { mock<NotificationRepository>() }
    val connectivityChecker: ConnectivityChecker by memoized { mock<ConnectivityChecker>() }

    val useCase: InsertNotificationUseCase by memoized {
        InsertNotificationUseCaseImpl(notificationRepository, connectivityChecker)
    }

    val notification = MyNotification("abc")
    val error = Throwable("error")
    val isOnline = true

    describe("insertNotification") {
        context("when inserting notification succeeds") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(notificationRepository.insertNotification(notification, isOnline)).willReturn(Completable.complete())

                testObserver = useCase.execute(notification).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }

        }

        context("when inserting notification failed") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(notificationRepository.insertNotification(notification, isOnline)).willReturn(Completable.error(error))

                testObserver = useCase.execute(notification).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})