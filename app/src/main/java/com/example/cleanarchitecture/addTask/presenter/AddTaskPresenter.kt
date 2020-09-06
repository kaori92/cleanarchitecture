package com.example.cleanarchitecture.addTask.presenter

import android.util.Log
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.addTask.models.AddTaskModel
import com.example.cleanarchitecture.addTask.view.AddTaskView
import com.example.cleanarchitecture.core.BasePresenter
import com.example.cleanarchitecture.core.SchedulerProvider
import com.example.cleanarchitecture.core.StringService
import com.example.cleanarchitecture.core.TAG
import com.example.cleanarchitecture.datasource.data.Task
import moxy.InjectViewState

@InjectViewState
class AddTaskPresenter(
    private val model: AddTaskModel,
    private val schedulerProvider: SchedulerProvider,
    private val stringService: StringService
) : BasePresenter<AddTaskView>() {

    fun insertOrUpdateTask(task: Task) {
        val disposable = model.insertOrUpdateTask(task)
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.showToast(
                    stringService.getStringResource(
                        R.string.task_added
                    )
                )
                viewState.openTaskList()
            }, {
                viewState.showError(it)
                Log.e(TAG, "$it ${it.message} ${it.stackTrace}")
            })

        compositeDisposable.add(disposable)
    }
}