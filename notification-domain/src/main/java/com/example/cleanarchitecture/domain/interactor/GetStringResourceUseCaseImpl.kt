package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.string.StringService

class GetStringResourceUseCaseImpl(
    private val stringService: StringService
): GetStringResourceUseCase {
    override fun execute(id: Int): String {
        return stringService.getStringResource(id)
    }
}