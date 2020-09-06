package com.example.cleanarchitecture.datasource.di

import com.example.cleanarchitecture.core.TASKS_API_URL
import com.example.cleanarchitecture.datasource.ApiTaskDataSource
import com.example.cleanarchitecture.datasource.TaskRetrofitService
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object DataSourceModule {

    @JvmStatic
    @Provides
    fun provideTaskRetrofitService(): TaskRetrofitService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(TASKS_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TaskRetrofitService::class.java)
    }

    @JvmStatic
    @Provides
    fun provideDataSource(
        taskRetrofitService: TaskRetrofitService
    ): TaskDataSource = ApiTaskDataSource(taskRetrofitService)

}