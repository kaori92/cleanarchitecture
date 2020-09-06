package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.core.DefaultStringService
import com.example.cleanarchitecture.core.MyApplication
import com.example.cleanarchitecture.core.StringService
import dagger.Module
import dagger.Provides

@Module
object StringServiceModule {
    @JvmStatic
    @Provides
    fun provideStringService(context: MyApplication): StringService =
        DefaultStringService(context)

}