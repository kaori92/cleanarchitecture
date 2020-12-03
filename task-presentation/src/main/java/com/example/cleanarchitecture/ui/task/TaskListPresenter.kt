package com.example.cleanarchitecture.ui.task

import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import moxy.InjectViewState

@InjectViewState
class TaskListPresenter(
    private val getTasksUseCase: GetTasksUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<TaskListView>() {

    fun getAllTasks() {
        val disposable = getTasksUseCase
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