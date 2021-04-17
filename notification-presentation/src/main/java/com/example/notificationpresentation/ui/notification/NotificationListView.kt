package com.example.notificationpresentation.ui.notification

import com.example.notificationdomain.model.MyNotification
import com.example.corepresentation.ui.core.BaseView
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