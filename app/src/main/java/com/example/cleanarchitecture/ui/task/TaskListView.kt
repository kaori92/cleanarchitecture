package com.example.cleanarchitecture.ui.task

import com.example.cleanarchitecture.ui.core.BaseView
import com.example.cleanarchitecture.domain.model.Task
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TaskListView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun setUpRecyclerView(tasks: Array<Task>)
}