package com.example.notificationpresentation.notification.presenter

import com.example.notificationdomain.interactor.definition.GetNotificationsUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.cleanarchitecture.scheduler.TestSchedulerProvider
import com.example.notificationpresentation.ui.notification.NotificationListPresenter
import com.example.notificationpresentation.ui.notification.NotificationListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class NotificationListPresenterTest : Spek({
    val getNotificationsUseCase: GetNotificationsUseCase by memoized { mock<GetNotificationsUseCase>() }
    val schedulerProvider = TestSchedulerProvider
    val view: NotificationListView by memoized { mock<NotificationListView>() }

    val presenter: NotificationListPresenter by memoized {
        NotificationListPresenter(
            getNotificationsUseCase,
            schedulerProvider
        )
    }
    val notifications = listOf(MyNotification("X"))
    val error = Throwable("error")

    describe("getting notifications") {

        context("when presenter gets notifications") {
            beforeEachTest {
                given(getNotificationsUseCase.execute()).willReturn(Single.just(notifications))

                presenter.attachView(view)
                presenter.getAllNotifications()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(notifications.toTypedArray())
            }

            it("should call showLoader") {
                verify(view).showLoader()
            }

            it("should hide loader") {
                verify(view).hideLoader()
            }
        }

        context("when error is returned") {
            beforeEachTest {
                given(getNotificationsUseCase.execute()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllNotifications()
            }

            it("should call showLoader") {
                verify(view).showLoader()
            }

            it("should show error") {
                verify(view).showError(any())
            }

            it("should hide loader") {
                verify(view).hideLoader()
            }
        }
    }

})