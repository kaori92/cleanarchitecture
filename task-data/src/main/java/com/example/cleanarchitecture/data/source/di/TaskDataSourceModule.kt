package com.example.cleanarchitecture.data.source.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.TASKS_API_URL
import com.example.cleanarchitecture.data.TASK_DATABASE_NAME
import com.example.cleanarchitecture.data.mapper.TaskApiDtoMapper
import com.example.cleanarchitecture.data.mapper.TaskDbEntityMapper
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.source.local.DefaultTaskDatabase
import com.example.cleanarchitecture.data.source.local.DefaultTaskLocalSource
import com.example.cleanarchitecture.data.source.remote.DefaultTaskRemoteSource
import com.example.cleanarchitecture.data.source.remote.TaskRetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class TaskDataSourceModule {

    @Provides
    fun provideDatabase(applicationContext: Context): DefaultTaskDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DefaultTaskDatabase::class.java, TASK_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideLocalDataSource(
        database: DefaultTaskDatabase,
        mapper: TaskDbEntityMapper
    ): TaskLocalSource = DefaultTaskLocalSource(database.taskDao(), mapper)

    @Provides
    fun provideRemoteDataSource(
        taskRetrofitService: TaskRetrofitService,
        mapperDb: TaskApiDtoMapper
    ): TaskRemoteSource = DefaultTaskRemoteSource(taskRetrofitService, mapperDb)

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