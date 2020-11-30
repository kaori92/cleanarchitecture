package com.example.cleanarchitecture.notification.presenter

import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.scheduler.TestSchedulerProvider
import com.example.cleanarchitecture.ui.notification.NotificationListPresenter
import com.example.cleanarchitecture.ui.notification.NotificationListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
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
        }

        context("when error is returned") {
            beforeEachTest {
                given(getNotificationsUseCase.execute()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllNotifications()
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }

        context("when subscribed") {
            beforeEachTest {
                val delayer: PublishSubject<Boolean> = PublishSubject.create()
                given(getNotificationsUseCase.execute()).willReturn(Single.just(notifications).delaySubscription(delayer))

                presenter.attachView(view)
                presenter.getAllNotifications()
            }

            it("should call showLoader") {
                verify(view).showLoader()
            }
        }

        context("finally") {
            beforeEachTest {
                val delayer: PublishSubject<Boolean> = PublishSubject.create()
                given(getNotificationsUseCase.execute()).willReturn(Single.just(notifications).delaySubscription(delayer))
                delayer.onComplete()

                presenter.attachView(view)
                presenter.getAllNotifications()
            }

            it("should call showLoader") {
                verify(view).hideLoader()
            }
        }
    }

})