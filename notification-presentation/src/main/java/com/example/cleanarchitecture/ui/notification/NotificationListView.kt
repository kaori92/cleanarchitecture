package com.example.cleanarchitecture.ui.notification

import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.ui.core.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface NotificationListView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun setUpRecyclerView(notifications: Array<MyNotification>)

    @StateStrategyType(AddToEndStrategy::class)
    fun hideLoader()

    @StateStrategyType(AddToEndStrategy::class)
    fun showLoader()
}