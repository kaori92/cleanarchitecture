package com.example.cleanarchitecture.string

import android.content.Context

class DefaultStringService(private val context: Context) : StringService {
    override fun getStringResource(id: Int): String {
        return context.getString(id)
    }
}