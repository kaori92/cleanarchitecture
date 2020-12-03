package com.example.cleanarchitecture.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecture.data.TITLE_FIELD_NAME
import com.google.gson.annotations.SerializedName

@Entity
data class NotificationDbEntity(
    @PrimaryKey @ColumnInfo(name = TITLE_FIELD_NAME) val title: String
)