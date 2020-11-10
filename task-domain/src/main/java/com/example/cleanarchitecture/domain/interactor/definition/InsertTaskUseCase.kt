package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable

interface InsertTaskUseCase {

    fun execute(task: Task) : Completable
}