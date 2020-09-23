package com.example.cleanarchitecture.ui.core

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

open class BasePresenter<MvpView : moxy.MvpView?> : MvpPresenter<MvpView>() {
    protected var compositeDisposable = CompositeDisposable()

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }

    override fun destroyView(view: MvpView) {
        super.destroyView(view)
        clearCompositeDisposable()
    }
}