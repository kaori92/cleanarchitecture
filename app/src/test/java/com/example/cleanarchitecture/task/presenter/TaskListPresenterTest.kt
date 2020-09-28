package com.example.cleanarchitecture.task.presenter

import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.platform.TestSchedulerProvider
import com.example.cleanarchitecture.ui.task.TaskListView
import com.example.cleanarchitecture.ui.task.TaskListPresenter
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
        TaskListPresenter(
            model,
            schedulerProvider
        )
    }

    val tasks = listOf(Task("X"))
    val error = Throwable("error")

    describe("getting tasks locally") {

        context("when presenter gets tasks") {

            beforeEachTest {
                given(model.getAllTasksLocally()).willReturn(Single.just(tasks))

                presenter.attachView(view)
                presenter.getAllTasksLocally()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(tasks.toTypedArray())
            }
        }

        context("when error is returned") {
            beforeEachTest {
                given(model.getAllTasksLocally()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllTasksLocally()
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }

    describe("getting tasks remotely") {
        context("when presenter gets tasks") {

            beforeEachTest {
                given(model.getAllTasksRemotely()).willReturn(Single.just(tasks))

                presenter.attachView(view)
                presenter.getAllTasksRemotely()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(tasks.toTypedArray())
            }
        }

        context("when error is returned") {

            beforeEachTest {
                given(model.getAllTasksRemotely()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllTasksRemotely()
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }
})