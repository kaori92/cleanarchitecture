package com.example.cleanarchitecture

import androidx.multidex.MultiDexApplication
import com.example.cleanarchitecture.di.module.AppModule
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.cleanarchitecture.di.DaggerApplicationComponent
import com.example.cleanarchitecture.di.module.DataSourceModule
import com.example.cleanarchitecture.di.module.StringServiceModule
import com.example.cleanarchitecture.di.module.TaskModule

class MyApplication : MultiDexApplication() {

    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .dataSourceModule(DataSourceModule())
            .taskModule(TaskModule())
            .stringServiceModule(StringServiceModule())
            .build()
    }

}