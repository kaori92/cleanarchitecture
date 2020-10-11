package com.example.cleanarchitecture.connectivity.di

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import dagger.Module
import dagger.Provides

@Module
class ConnectivityModule {

    @Provides
    fun provideConnectivityChecker() = ConnectivityChecker()
}