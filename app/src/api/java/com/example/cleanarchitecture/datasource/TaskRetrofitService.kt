package com.example.cleanarchitecture.datasource

import com.example.cleanarchitecture.core.TODOS_FIELD_NAME
import com.example.cleanarchitecture.datasource.data.Task
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskRetrofitService {

    @GET(TODOS_FIELD_NAME)
    fun getTasks(): Single<Array<Task>>

    @POST(TODOS_FIELD_NAME)
    fun addTask(@Body taskRequest: Task): Completable
}