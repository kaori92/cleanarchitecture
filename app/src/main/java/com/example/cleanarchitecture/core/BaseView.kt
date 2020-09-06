package com.example.cleanarchitecture.core

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun showError(error: Throwable)
}