package com.example.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.DefaultAddTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import com.example.cleanarchitecture.string.StringService
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AddTaskModelTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    val taskRepository: TaskRepository by memoized { mock<TaskRepository>() }
    val stringService: StringService by memoized { mock<StringService>() }
    val connectivityChecker: ConnectivityChecker by memoized { mock<ConnectivityChecker>() }

    val model: AddTaskModel by memoized {
        DefaultAddTaskModel(taskRepository, stringService, connectivityChecker)
    }

    val task = Task("abc")
    val error = Throwable("error")
    val isOnline = true

    describe("insertTask") {
        context("when inserting task succeeds") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(taskRepository.insertTask(task, isOnline)).willReturn(Completable.complete())

                testObserver = model.insertTask(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }

        }

        context("when inserting task failed") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(taskRepository.insertTask(task, isOnline)).willReturn(Completable.error(error))

                testObserver = model.insertTask(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})