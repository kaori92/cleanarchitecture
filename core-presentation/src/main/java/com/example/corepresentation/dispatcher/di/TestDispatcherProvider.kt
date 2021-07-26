package com.example.corepresentation.dispatcher.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object TestDispatcherProvider : DispatcherProvider {

    override fun io(): CoroutineDispatcher = Dispatchers.Unconfined
}