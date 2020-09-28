package com.example.cleanarchitecture.ui.addTask

import android.util.Log
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.platform.SchedulerProvider
import com.example.cleanarchitecture.platform.StringService
import com.example.cleanarchitecture.platform.TAG
import com.example.cleanarchitecture.domain.model.Task
import moxy.InjectViewState

@InjectViewState
class AddTaskPresenter(
    private val model: AddTaskModel,
    private val schedulerProvider: SchedulerProvider,
    private val stringService: StringService
) : BasePresenter<AddTaskView>() {

    fun insertTaskRemotely(task: Task) {
        val disposable = model.insertTaskRemotely(task)
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

    fun insertTaskLocally(task: Task) {
        val disposable = model.insertTaskLocally(task)
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