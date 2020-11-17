package com.example.cleanarchitecture.ui.addNotification

import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.interactor.definition.InsertNotificationUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.cleanarchitecture.TAG
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
                viewState.showNotification(notification)
                viewState.showToast(
                    getStringUseCase.execute(
                        R.string.notification_added
                    )
                )
                viewState.showNotification(notification)
                viewState.openNotificationList()
            }, {
                viewState.showError(it)
                Log.e(TAG, "$it ${it.message} ${it.stackTrace}")
            })

        compositeDisposable.add(disposable)
    }
}