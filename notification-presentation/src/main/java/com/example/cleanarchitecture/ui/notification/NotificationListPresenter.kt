package com.example.cleanarchitecture.ui.notification

import android.util.Log
import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.TAG
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import moxy.InjectViewState

@InjectViewState
class NotificationListPresenter(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<NotificationListView>() {

    fun getAllNotifications() {
        val disposable = getNotificationsUseCase
            .execute()
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                showLoader()
                viewState.setUpRecyclerView(it.toTypedArray())
                hideLoader()
            }, {
                showLoader()
                viewState.showError(it)
                Log.e(TAG, "$it ${it.localizedMessage} ${it.stackTrace}")
                hideLoader()
            })

        compositeDisposable.add(disposable)
    }

    private fun hideLoader() {
        viewState.hideLoader()
    }

    private fun showLoader() {
        viewState.showLoader()
    }
}