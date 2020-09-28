package com.example.cleanarchitecture.ui.task

import android.util.Log
import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.platform.SchedulerProvider
import com.example.cleanarchitecture.platform.TAG
import moxy.InjectViewState

@InjectViewState
class TaskListPresenter(
    private val model: TaskModel,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<TaskListView>() {

    fun getAllTasksLocally() {
        val disposable = model.getAllTasksLocally()
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.setUpRecyclerView(it.toTypedArray())
            }, {
                viewState.showError(it)
                Log.e(TAG, "$it ${it.localizedMessage} ${it.stackTrace}")
            })

        compositeDisposable.add(disposable)
    }

    fun getAllTasksRemotely() {
        val disposable = model.getAllTasksRemotely()
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.setUpRecyclerView(it.toTypedArray())
            }, {
                viewState.showError(it)
                Log.e(TAG, "$it ${it.localizedMessage} ${it.stackTrace}")
            })

        compositeDisposable.add(disposable)
    }
}