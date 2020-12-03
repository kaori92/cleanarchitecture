package com.example.cleanarchitecture.ui.addNotification

import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.ui.core.BaseView
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