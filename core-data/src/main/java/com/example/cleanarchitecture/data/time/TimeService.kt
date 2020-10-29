package com.example.cleanarchitecture.data.time

interface TimeService {
    fun getTime(): Long
    fun getCacheLimitMs(): Int
    fun getCacheTimestampMs(): Long
    fun setCacheTimestampMs(limitMs: Long)
}