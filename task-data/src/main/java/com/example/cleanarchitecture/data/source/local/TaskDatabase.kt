package com.example.cleanarchitecture.data.source.local

import com.example.cleanarchitecture.data.source.local.dao.TaskDao

interface TaskDatabase {
    fun taskDao(): TaskDao
}