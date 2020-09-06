package com.example.cleanarchitecture.task.di

import com.example.cleanarchitecture.task.presenter.TaskListPresenter
import dagger.Subcomponent

@Subcomponent(modules = [TaskModule::class])
interface TaskComponent {
    fun presenter(): TaskListPresenter

    @Subcomponent.Builder
    interface Builder {
        fun build(): TaskComponent
    }
}
