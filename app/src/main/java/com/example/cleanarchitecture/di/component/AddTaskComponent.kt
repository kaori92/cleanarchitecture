package com.example.cleanarchitecture.di.component


import com.example.cleanarchitecture.di.module.AddTaskModule
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
import dagger.Subcomponent

@Subcomponent(modules = [AddTaskModule::class])
interface AddTaskComponent {
    fun presenter(): AddTaskPresenter

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddTaskComponent
    }
}
