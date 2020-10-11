package com.example.domain.interactor

import com.example.cleanarchitecture.domain.interactor.DefaultAddTaskModel
import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.domain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AddTaskModelTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    val taskRepository: TaskRepository by memoized {
        mock<TaskRepository>()
    }

    val model: AddTaskModel by memoized {
        DefaultAddTaskModel(taskRepository)
    }
    val task = Task("abc")
    val error = Throwable("error")

    describe("insertTask") {
        context("when inserting task locally") {
            beforeEachTest {
                given(taskRepository.insertTaskLocally(task)).willReturn(Completable.complete())

                testObserver = model.insertTaskLocally(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task locally failed") {
            beforeEachTest {
                given(taskRepository.insertTaskLocally(task)).willReturn(Completable.error(error))

                testObserver = model.insertTaskLocally(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }

        context("when inserting task remotely") {
            beforeEachTest {
                given(taskRepository.insertTaskRemotely(task)).willReturn(Completable.complete())

                testObserver = model.insertTaskRemotely(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task remotely failed") {
            beforeEachTest {
                given(taskRepository.insertTaskRemotely(task)).willReturn(Completable.error(error))

                testObserver = model.insertTaskRemotely(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }
})