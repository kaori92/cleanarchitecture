package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable

interface AddTaskModel {

    fun insertTask(task: Task): Completable
    fun getStringResource(id: Int): String
}