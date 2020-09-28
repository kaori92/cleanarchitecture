package com.example.cleanarchitecture.di.module

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.mapper.TaskApiDtoMapper
import com.example.cleanarchitecture.data.mapper.TaskDbEntityMapper
import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.data.source.local.TaskDatabase
import com.example.cleanarchitecture.data.source.local.TaskLocalSource
import com.example.cleanarchitecture.data.source.remote.TaskRemoteSource
import com.example.cleanarchitecture.data.source.remote.TaskRetrofitService
import com.example.cleanarchitecture.platform.TASKS_API_URL
import com.example.cleanarchitecture.platform.TASK_DATABASE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideDatabase(applicationContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, TASK_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: TaskDatabase,
        mapper: TaskDbEntityMapper
    ): LocalSource = TaskLocalSource(database.taskDao(), mapper)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        taskRetrofitService: TaskRetrofitService,
        mapperDb: TaskApiDtoMapper
    ): RemoteSource = TaskRemoteSource(taskRetrofitService, mapperDb)

    @Singleton
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

}