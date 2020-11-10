package com.example.cleanarchitecture.ui.addTask

import android.util.Log
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.interactor.definition.InsertTaskUseCase
import com.example.cleanarchitecture.ui.core.BasePresenter
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.cleanarchitecture.TAG
import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.domain.model.Task
import moxy.InjectViewState

@InjectViewState
class AddTaskPresenter(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<AddTaskView>() {

    fun insertTask(task: Task) {
        val disposable = insertTaskUseCase.execute(task)
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                viewState.showToast(
                    getStringUseCase.execute(
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