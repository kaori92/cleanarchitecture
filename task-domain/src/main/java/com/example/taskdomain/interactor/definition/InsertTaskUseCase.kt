package com.example.taskdomain.interactor.definition

import com.example.taskdomain.model.Task
import io.reactivex.Completable

interface InsertTaskUseCase {

    fun execute(task: Task) : Completable
}