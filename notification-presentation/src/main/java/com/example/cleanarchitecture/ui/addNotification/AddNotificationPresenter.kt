package com.example.cleanarchitecture.ui.addNotification

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.interactor.definition.InsertNotificationUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.domain.model.MyNotification
import moxy.InjectViewState

@InjectViewState
class AddNotificationPresenter(
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val getStringUseCase: GetStringResourceUseCase,
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