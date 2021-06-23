package com.example.cleanarchitecture.time.di

import com.example.cleanarchitecture.time.DefaultTimeService
import com.example.cleanarchitecture.time.TimeService
import dagger.Module
import dagger.Provides

@Module
class TimeServiceModule {

    @Provides
    fun provideTimeService(): TimeService =
        DefaultTimeService()
}