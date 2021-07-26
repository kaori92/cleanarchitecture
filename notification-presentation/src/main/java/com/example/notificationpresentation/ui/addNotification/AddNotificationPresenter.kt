package com.example.notificationpresentation.ui.addNotification

import com.example.notificationdomain.interactor.definition.InsertNotificationUseCase
import com.example.corepresentation.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.notificationdomain.interactor.definition.GetStringResourceNotificationUseCase
import com.example.notificationdomain.model.MyNotification
import com.example.notificationpresentation.R
import moxy.InjectViewState

@InjectViewState
class AddNotificationPresenter(
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val getStringUseCase: GetStringResourceNotificationUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<AddNotificationView>() {

    fun insertNotification(notification: MyNotification) {
        val disposable = insertNotificationUseCase.execute(notification)
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.showToast(
                    getStringUseCase.execute(
                        R.string.notification_added
                    )
                )
                viewState.showNotification(notification)
                viewState.openNotificationList()
            }, {
                viewState.showError(it)
            })

        compositeDisposable.add(disposable)
    }
}