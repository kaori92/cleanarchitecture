package com.example.cleanarchitecture.datasource.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecture.core.TITLE_FIELD_NAME

@Entity
data class Task(
    @PrimaryKey @ColumnInfo(name = TITLE_FIELD_NAME) val title: String
)