package com.example.cleanarchitecture.ui.addTask

import com.example.cleanarchitecture.ui.core.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddTaskView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun openTaskList()

    @StateStrategyType(AddToEndStrategy::class)
    fun showToast(text: String)
}