package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.TODOS_FIELD_NAME
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskRetrofitService {

    @GET(TODOS_FIELD_NAME)
    fun getTasks(): Single<List<TaskApiDto>>

    @POST(TODOS_FIELD_NAME)
    fun addTask(@Body taskRequest: TaskApiDto): Completable
}