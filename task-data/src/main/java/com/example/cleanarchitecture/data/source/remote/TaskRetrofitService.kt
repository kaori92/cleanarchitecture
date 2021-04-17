package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.TODOS_FIELD_NAME
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import io.reactivex.Completable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskRetrofitService {

    @GET(TODOS_FIELD_NAME)
    suspend fun getTasks(): List<TaskApiDto>

    @POST(TODOS_FIELD_NAME)
    fun insertTask(@Body taskRequest: TaskApiDto): Call<Any>
}