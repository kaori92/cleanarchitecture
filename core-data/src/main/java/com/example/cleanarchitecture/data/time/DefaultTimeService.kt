package com.example.cleanarchitecture.data.time

import android.os.SystemClock

private const val CACHE_LIMIT_MS = 60 * 1000

class DefaultTimeService: TimeService {
    private var cacheTimestampMs: Long = 0

    override fun getTime() = SystemClock.elapsedRealtime()
    override fun getCacheLimitMs() = CACHE_LIMIT_MS
    override fun getCacheTimestampMs() = cacheTimestampMs
    override fun setCacheTimestampMs(limitMs: Long) {
        cacheTimestampMs = limitMs
    }
}