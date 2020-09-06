package com.example.cleanarchitecture.addTask.di


import com.example.cleanarchitecture.addTask.presenter.AddTaskPresenter
import dagger.Subcomponent

@Subcomponent(modules = [AddTaskModule::class])
interface AddTaskComponent {
    fun presenter(): AddTaskPresenter

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddTaskComponent
    }
}
