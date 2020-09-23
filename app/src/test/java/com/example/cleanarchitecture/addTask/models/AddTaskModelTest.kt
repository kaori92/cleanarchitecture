package com.example.cleanarchitecture.addTask.models

import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.data.source.TaskDataSource
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AddTaskModelTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    val taskDataSource: com.example.cleanarchitecture.data.source.TaskDataSource by memoized {
        mock<com.example.cleanarchitecture.data.source.TaskDataSource>()
    }

    val model: AddTaskModel by memoized {
        DefaultAddTaskModel(taskDataSource)
    }

    describe("insert Or Update Task") {
        val task = com.example.cleanarchitecture.domain.model.Task("abc")

        context("when inserting task") {
            beforeEachTest {
                given(taskDataSource.insertOrUpdateTask(task)).willReturn(Completable.complete())

                testObserver = model.insertOrUpdateTask(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task failed") {
            val error = Throwable("error")

            beforeEachTest {
                given(taskDataSource.insertOrUpdateTask(task)).willReturn(Completable.error(error))

                testObserver = model.insertOrUpdateTask(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }

    }
})