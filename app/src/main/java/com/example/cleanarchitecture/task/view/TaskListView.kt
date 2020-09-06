package com.example.cleanarchitecture.task.view

import com.example.cleanarchitecture.core.BaseView
import com.example.cleanarchitecture.datasource.data.Task
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TaskListView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun setUpRecyclerView(tasks: Array<Task>)

    @StateStrategyType(AddToEndStrategy::class)
    fun refresh()
}