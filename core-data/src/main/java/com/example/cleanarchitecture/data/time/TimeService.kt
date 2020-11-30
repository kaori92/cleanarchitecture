package com.example.cleanarchitecture.data.time

interface TimeService {
    var cacheTimestampMs: Long

    fun getTime(): Long
    fun getCacheLimitMs(): Int
    fun updateCacheTimestampMs()
    fun isTimeoutExceeded(): Boolean
}