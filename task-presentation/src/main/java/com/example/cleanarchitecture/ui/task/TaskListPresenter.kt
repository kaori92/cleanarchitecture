package com.example.cleanarchitecture.ui.task

import android.util.Log
import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.TAG
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