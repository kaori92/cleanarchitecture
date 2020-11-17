package com.example.cleanarchitecture.domain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.domain.interactor.definition.GetTasksUseCase
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetTasksUseCaseTest : Spek({
    val taskRepository: TaskRepository by memoized { mock<TaskRepository>() }
    val connectivityChecker: ConnectivityChecker by memoized { mock<ConnectivityChecker>() }

    val model: GetTasksUseCase by memoized {
        GetTasksUseCaseImpl(
            taskRepository,
            connectivityChecker
        )
    }

    val tasks = listOf(Task("x"))
    val error = Throwable("error")
    val isOnline = true

    describe("get all tasks") {
        lateinit var testObserver: TestObserver<List<Task>>

        context("when getting all tasks succeeds") {

            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(taskRepository.getAllTasks(isOnline)).willReturn(Single.just(tasks))

                testObserver = model.execute().test()
            }


            it("should complete") {
                testObserver.assertComplete()
            }

            it("should return correct tasks") {
                testObserver.assertResult(tasks)
            }
        }

        context("when getting all tasks failed") {
            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(taskRepository.getAllTasks(isOnline)).willReturn(Single.error(error))

                testObserver = model.execute().test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})