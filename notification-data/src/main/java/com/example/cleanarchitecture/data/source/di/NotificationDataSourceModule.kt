package com.example.cleanarchitecture.data.source.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.NOTIFICATIONS_API_URL
import com.example.cleanarchitecture.data.NOTIFICATION_DATABASE_NAME
import com.example.cleanarchitecture.data.mapper.NotificationApiDtoMapper
import com.example.cleanarchitecture.data.mapper.NotificationDbEntityMapper
import com.example.cleanarchitecture.data.source.NotificationLocalSource
import com.example.cleanarchitecture.data.source.NotificationRemoteSource
import com.example.cleanarchitecture.data.source.local.DefaultNotificationDatabase
import com.example.cleanarchitecture.data.source.local.DefaultNotificationLocalSource
import com.example.cleanarchitecture.data.source.remote.DefaultNotificationRemoteSource
import com.example.cleanarchitecture.data.source.remote.NotificationRetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NotificationDataSourceModule {

    @Provides
    fun provideDatabase(applicationContext: Context): DefaultNotificationDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DefaultNotificationDatabase::class.java, NOTIFICATION_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideLocalDataSource(
        database: DefaultNotificationDatabase,
        mapper: NotificationDbEntityMapper
    ): NotificationLocalSource = DefaultNotificationLocalSource(database.notificationDao(), mapper)

    @Provides
    fun provideRemoteDataSource(
        notificationRetrofitService: NotificationRetrofitService,
        mapperDb: NotificationApiDtoMapper
    ): NotificationRemoteSource = DefaultNotificationRemoteSource(notificationRetrofitService, mapperDb)

    @Provides
    fun provideNotificationRetrofitService(): NotificationRetrofitService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(NOTIFICATIONS_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NotificationRetrofitService::class.java)
    }

}