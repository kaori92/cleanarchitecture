package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.platform.DefaultStringService
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.platform.StringService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StringServiceModule {
    @Singleton
    @Provides
    fun provideStringService(context: MyApplication): StringService =
        DefaultStringService(context)

}