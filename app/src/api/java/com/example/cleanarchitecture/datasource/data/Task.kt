package com.example.cleanarchitecture.datasource.data

import androidx.room.Entity
import com.example.cleanarchitecture.core.TITLE_FIELD_NAME
import com.google.gson.annotations.SerializedName

@Entity
data class Task(
    @SerializedName(TITLE_FIELD_NAME) val title: String
)