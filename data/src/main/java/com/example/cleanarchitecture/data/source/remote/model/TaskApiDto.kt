package com.example.cleanarchitecture.data.source.remote.model

import com.example.cleanarchitecture.data.TITLE_FIELD_NAME
import com.google.gson.annotations.SerializedName

data class TaskApiDto(
    @SerializedName(TITLE_FIELD_NAME) val title: String
)