package com.example.corepresentation.dispatcher.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object AndroidDispatcherProvider: DispatcherProvider {

    override fun io(): CoroutineDispatcher = Dispatchers.IO
}