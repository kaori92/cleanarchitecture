package com.example.cleanarchitecture.domain.interactor.definition

import com.example.cleanarchitecture.domain.model.Task
import io.reactivex.Completable

interface AddTaskModel {

    fun insertTaskRemotely(task: Task): Completable
    fun insertTaskLocally(task: Task): Completable
}