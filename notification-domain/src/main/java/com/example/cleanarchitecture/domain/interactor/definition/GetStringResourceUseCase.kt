package com.example.cleanarchitecture.domain.interactor.definition

interface GetStringResourceUseCase {
    fun execute(id: Int): String
}