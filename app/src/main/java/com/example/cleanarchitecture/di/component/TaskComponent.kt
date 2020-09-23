package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.ui.task.TaskListPresenter
import dagger.Subcomponent

@Subcomponent
interface TaskComponent {
    fun presenter(): TaskListPresenter

    @Subcomponent.Builder
    interface Builder {
        fun build(): TaskComponent
    }
}
