package com.example.cleanarchitecture.addTask.view

import com.example.cleanarchitecture.core.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddTaskView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun openTaskList()

    @StateStrategyType(AddToEndStrategy::class)
    fun showToast(text: String)
}