package com.example.notificationdomain.interactor

import com.example.notificationdomain.interactor.definition.GetStringResourceNotificationUseCase
import com.example.cleanarchitecture.string.StringService

class GetStringResourceNotificationUseCaseImpl(
    private val stringService: StringService
): GetStringResourceNotificationUseCase {
    override fun execute(id: Int): String {
        return stringService.getStringResource(id)
    }
}