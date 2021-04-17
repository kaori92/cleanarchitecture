package com.example.notificationpresentation.ui.addNotification

import com.example.notificationdomain.model.MyNotification
import com.example.corepresentation.ui.core.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddNotificationView : BaseView {

    @StateStrategyType(AddToEndStrategy::class)
    fun openNotificationList()

    @StateStrategyType(AddToEndStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(AddToEndStrategy::class)
    fun showNotification(notification: MyNotification)
}