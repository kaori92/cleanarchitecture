package com.example.cleanarchitecture.task.presenter

import com.example.cleanarchitecture.core.TestSchedulerProvider
import com.example.cleanarchitecture.datasource.data.Task
import com.example.cleanarchitecture.task.models.TaskModel
import com.example.cleanarchitecture.task.view.TaskListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskListPresenterTest : Spek({
    val schedulerProvider = TestSchedulerProvider
    val model: TaskModel by memoized { mock<TaskModel>() }
    val view: TaskListView by memoized { mock<TaskListView>() }

    val presenter: TaskListPresenter by memoized {
        TaskListPresenter(model, schedulerProvider)
    }

    describe("getting tasks") {
        val tasks = arrayOf(Task("X"))
        context("when presenter gets tasks") {

            beforeEachTest {
                given(model.getAllTasks()).willReturn(Single.just(tasks))

                presenter.attachView(view)
                presenter.getAllTasks()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(tasks)
            }
        }

        context("when error is returned") {
            val error = Throwable("error")

            beforeEachTest {
                given(model.getAllTasks()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllTasks()
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }
})