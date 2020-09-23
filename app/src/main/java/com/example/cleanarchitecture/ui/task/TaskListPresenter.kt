package com.example.cleanarchitecture.ui.task

import android.util.Log
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.platform.SchedulerProvider
import com.example.cleanarchitecture.platform.TAG
import com.example.cleanarchitecture.task.models.TaskModel
import moxy.InjectViewState

@InjectViewState
class TaskListPresenter(
    private val model: TaskModel,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<TaskListView>() {

    fun getAllTasks() {
        val disposable = model.getAllTasks()
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.setUpRecyclerView(it)
            }, {
                viewState.showError(it)
                Log.e(TAG, "$it ${it.localizedMessage} ${it.stackTrace}")
            })

        compositeDisposable.add(disposable)
    }
}