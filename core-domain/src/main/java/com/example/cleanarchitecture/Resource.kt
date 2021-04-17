package com.example.cleanarchitecture

class Resource<T> private constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status {
        SUCCESS, ERROR, LOADING, STOP_LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)

        fun <T> stopLoading(): Resource<T> = Resource(Status.STOP_LOADING, null, null)
    }
}