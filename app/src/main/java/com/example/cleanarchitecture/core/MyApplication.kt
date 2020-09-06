package com.example.cleanarchitecture.core

import androidx.multidex.MultiDexApplication
import com.example.cleanarchitecture.di.AppModule
import com.example.cleanarchitecture.di.ApplicationComponent
import com.example.cleanarchitecture.di.DaggerApplicationComponent

class MyApplication : MultiDexApplication() {

    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}