package com.example.cleanarchitecture.di.component

import android.content.Context
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.di.module.*
import com.example.cleanarchitecture.string.di.StringServiceModule
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataSourceModule::class,
        StringServiceModule::class
    ]
)
interface ApplicationComponent {

    fun getApplication(): MyApplication

    fun getApplicationContext(): Context
}