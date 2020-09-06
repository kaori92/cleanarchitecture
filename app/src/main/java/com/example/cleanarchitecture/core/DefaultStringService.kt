package com.example.cleanarchitecture.core

import android.content.Context

class DefaultStringService(private val context: Context) : StringService {
    override fun getStringResource(id: Int): String {
        return context.getString(id)
    }
}

interface StringService {
    fun getStringResource(id: Int): String
}