package com.example.cleanarchitecture.di

import android.content.Context
import com.example.cleanarchitecture.core.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplication: MyApplication) {

    @Provides
    internal fun provideApplication(): MyApplication {
        return mApplication
    }

    @Provides
    fun provideContext(): Context {
        return mApplication.applicationContext
    }

}
