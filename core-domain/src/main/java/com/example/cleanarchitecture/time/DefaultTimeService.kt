package com.example.cleanarchitecture.time

import android.os.SystemClock

private const val CACHE_LIMIT_MS = 60 * 1000

class DefaultTimeService: TimeService {
    override var cacheTimestampMs: Long = SystemClock.elapsedRealtime()

    override fun getTime() = SystemClock.elapsedRealtime()
    override fun getCacheLimitMs() = CACHE_LIMIT_MS

    override fun updateCacheTimestampMs() {
        cacheTimestampMs = getTime()
    }

    override fun isTimeoutExceeded() = getTime() - cacheTimestampMs > getCacheLimitMs()
}