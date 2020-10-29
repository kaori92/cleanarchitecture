package com.example.cleanarchitecture.connectivity.di

import android.content.Context
import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.connectivity.DefaultConnectivityChecker
import dagger.Module
import dagger.Provides

@Module
class ConnectivityModule {

    @Provides
    fun provideConnectivityChecker(context: Context): ConnectivityChecker = DefaultConnectivityChecker(context)
}