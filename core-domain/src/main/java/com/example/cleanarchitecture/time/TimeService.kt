package com.example.cleanarchitecture.time

interface TimeService {
    var cacheTimestampMs: Long

    fun getTime(): Long
    fun getCacheLimitMs(): Int
    fun updateCacheTimestampMs()
    fun isTimeoutExceeded(): Boolean
}