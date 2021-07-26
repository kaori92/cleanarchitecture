package com.example.cleanarchitecture.notificationdata.source.remote.model

import com.example.cleanarchitecture.notificationdata.TITLE_FIELD_NAME
import com.google.gson.annotations.SerializedName

data class NotificationApiDto(
    @SerializedName(TITLE_FIELD_NAME) val title: String
)