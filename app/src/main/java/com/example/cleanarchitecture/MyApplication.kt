package com.example.cleanarchitecture

import androidx.multidex.MultiDexApplication
import com.example.cleanarchitecture.di.module.AppModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.cleanarchitecture.di.component.DaggerApplicationComponent

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