package com.example.cleanarchitecture.ui.notification

import com.example.cleanarchitecture.domain.interactor.definition.GetNotificationsUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
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
            .doOnSubscribe {
                viewState.showLoader()
            }
            .doFinally {
                viewState.hideLoader()
            }
            .subscribe({
                viewState.setUpRecyclerView(it.toTypedArray())
            }, {
                viewState.showError(it)
            })

        compositeDisposable.add(disposable)
    }
}