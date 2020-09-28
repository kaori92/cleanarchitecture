package com.example.domain.interactor

import com.example.cleanarchitecture.domain.interactor.DefaultTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskModelTest : Spek({
    val taskRepository: TaskRepository by memoized {
        mock<TaskRepository>()
    }

    val model: TaskModel by memoized {
        DefaultTaskModel(taskRepository)
    }

    val tasks = listOf(Task("x"))
    val error = Throwable("error")

    describe("get all tasks") {
        lateinit var testObserver: TestObserver<List<Task>>

        context("when getting all tasks locally") {

            beforeEachTest {
                given(taskRepository.getAllTasksLocally()).willReturn(Single.just(tasks))

                testObserver = model.getAllTasksLocally().test()
            }


            it("should complete") {
                testObserver.assertComplete()
            }

            it("should return correct tasks") {
                testObserver.assertResult(tasks)
            }
        }

        context("when getting all tasks locally failed") {
            beforeEachTest {
                given(taskRepository.getAllTasksLocally()).willReturn(Single.error(error))

                testObserver = model.getAllTasksLocally().test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }

        context("when getting all tasks remotely") {
            beforeEachTest {
                given(taskRepository.getAllTasksRemotely()).willReturn(Single.just(tasks))

                testObserver = model.getAllTasksRemotely().test()
            }


            it("should complete") {
                testObserver.assertComplete()
            }

            it("should return correct tasks") {
                testObserver.assertResult(tasks)
            }
        }

        context("when getting all tasks remotely failed") {
            beforeEachTest {
                given(taskRepository.getAllTasksRemotely()).willReturn(Single.error(error))

                testObserver = model.getAllTasksRemotely().test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})