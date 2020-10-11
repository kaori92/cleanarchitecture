package com.example.cleanarchitecture.ui.addTask

import android.content.Context
import android.util.Log
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.cleanarchitecture.string.StringService
import com.example.cleanarchitecture.TAG
import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.model.Task
import moxy.InjectViewState

@InjectViewState
class AddTaskPresenter(
    private val model: AddTaskModel,
    private val schedulerProvider: SchedulerProvider,
    private val stringService: StringService,
    private val connectivityChecker: ConnectivityChecker,
    private val context: Context
) : BasePresenter<AddTaskView>() {

    fun insertTask(task: Task) {
        val disposable = model.insertTask(task, connectivityChecker.isOnline(context))
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