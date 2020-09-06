package com.example.cleanarchitecture.task.models

import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.datasource.TaskDataSource
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskModelTest : Spek({
    val taskDataSource: TaskDataSource by memoized {
        mock<TaskDataSource>()
    }

    val model: TaskModel by memoized {
        DefaultTaskModel(taskDataSource)
    }

    describe("get all tasks") {
        lateinit var testObserver: TestObserver<Array<Task>>

        context("when getting all tasks") {

            val tasks = arrayOf(Task("x"))

            beforeEachTest {
                given(taskDataSource.getAllTasks()).willReturn(Single.just(tasks))

                testObserver = model.getAllTasks().test()
            }


            it("should complete") {
                testObserver.assertComplete()
            }

            it("should return correct tasks") {
                testObserver.assertResult(tasks)
            }
        }

        context("when getting all tasks failed") {
            val error = Throwable("error")

            beforeEachTest {
                given(taskDataSource.getAllTasks()).willReturn(Single.error(error))

                testObserver = model.getAllTasks().test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }

    }
})