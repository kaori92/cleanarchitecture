package com.example.taskpresentation.viewmodel.addTask

import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.taskdomain.model.Task
import com.example.cleanarchitecture.Resource

class AddTaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getStringUseCase: GetStringResourceUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    var taskResource: Resource<Task>? = null

    fun insertTask(task: Task) {
        insertTaskUseCase.execute(task)
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    taskResource = Resource.success(task)
                }, {
                    taskResource = Resource.error(
                        msg = "Error inserting task $task: ${it.localizedMessage} ",
                        data = null
                    )
                }
            )
    }

    fun getString(id: Int) = getStringUseCase.execute(id)
}