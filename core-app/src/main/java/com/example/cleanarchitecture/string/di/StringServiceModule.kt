package com.example.cleanarchitecture.string.di

import com.example.cleanarchitecture.string.DefaultStringService
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.string.StringService
import dagger.Module
import dagger.Provides

@Module
class StringServiceModule {

    @Provides
    fun provideStringService(context: MyApplication): StringService =
        DefaultStringService(context)

}