package com.example.cleanarchitecture.data.time.di

import com.example.cleanarchitecture.data.time.DefaultTimeService
import com.example.cleanarchitecture.data.time.TimeService
import dagger.Module
import dagger.Provides

@Module
class TimeServiceModule {

    @Provides
    fun provideTimeService(): TimeService =
        DefaultTimeService()
}