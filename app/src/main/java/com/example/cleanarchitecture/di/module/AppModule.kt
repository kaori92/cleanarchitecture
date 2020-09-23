package com.example.cleanarchitecture.di.module

import android.content.Context
import com.example.cleanarchitecture.MyApplication
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
