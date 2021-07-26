package com.example.notificationpresentation.addNotification.presenter

import com.example.notificationdomain.interactor.definition.GetStringResourceNotificationUseCase
import com.example.notificationdomain.interactor.definition.InsertNotificationUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.notificationpresentation.ui.addNotification.AddNotificationView
import com.example.cleanarchitecture.scheduler.TestSchedulerProvider
import com.example.notificationpresentation.ui.addNotification.AddNotificationPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AddNotificationPresenterTest : Spek({
    val schedulerProvider = TestSchedulerProvider
    val insertNotificationUseCase: InsertNotificationUseCase by memoized { mock<InsertNotificationUseCase>() }
    val getStringUseCase: GetStringResourceNotificationUseCase by memoized { mock<GetStringResourceNotificationUseCase>() }
    val view: AddNotificationView by memoized { mock<AddNotificationView>() }

    val presenter: AddNotificationPresenter by memoized {
        AddNotificationPresenter(
            insertNotificationUseCase,
            getStringUseCase,
            schedulerProvider
        )
    }

    val notification = MyNotification("xyz")
    val error = Throwable("error")
    val message = "notification added"

    describe("inserting notifications") {
        context("when presenter inserts notification") {

            beforeEachTest {
                given(getStringUseCase.execute(any())).willReturn(message)
                given(insertNotificationUseCase.execute(notification)).willReturn(Completable.complete())

                presenter.attachView(view)
                presenter.insertNotification(notification)
            }

            it("should show notification") {
                verify(view).showNotification(notification)
            }

            it("should call view openNotificationList") {
                verify(view).openNotificationList()
            }

            it("should call view showToast") {
                verify(view).showToast(message)
            }
        }

        context("when error is returned") {
            beforeEachTest {
                given(insertNotificationUseCase.execute(notification)).willReturn(Completable.error(error))

                presenter.attachView(view)
                presenter.insertNotification(notification)
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }

})