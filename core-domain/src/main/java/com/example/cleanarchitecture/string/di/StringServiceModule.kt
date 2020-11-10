package com.example.cleanarchitecture.string.di

import android.content.Context
import com.example.cleanarchitecture.string.DefaultStringService
import com.example.cleanarchitecture.string.StringService
import dagger.Module
import dagger.Provides

@Module
class StringServiceModule {

    @Provides
    fun provideStringService(context: Context): StringService =
        DefaultStringService(context)

}