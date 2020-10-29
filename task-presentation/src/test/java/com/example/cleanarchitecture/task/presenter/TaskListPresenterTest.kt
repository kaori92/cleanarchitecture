package com.example.cleanarchitecture.task.presenter

import com.example.cleanarchitecture.domain.interactor.definition.TaskModel
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.scheduler.SchedulerProvider
import com.example.cleanarchitecture.ui.task.TaskListPresenter
import com.example.cleanarchitecture.ui.task.TaskListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskListPresenterTest : Spek({
    val model: TaskModel by memoized { mock<TaskModel>() }
    val schedulerProvider: SchedulerProvider by memoized { mock<SchedulerProvider>() }
    val view: TaskListView by memoized { mock<TaskListView>() }

    val presenter: TaskListPresenter by memoized {
        TaskListPresenter(
            model,
            schedulerProvider
        )
    }
    val tasks = listOf(Task("X"))
    val error = Throwable("error")

    describe("getting tasks") {

        context("when presenter gets tasks") {
            beforeEachTest {
                given(model.getAllTasks()).willReturn(Single.just(tasks))

                presenter.attachView(view)
                presenter.getAllTasks()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(tasks.toTypedArray())
            }
        }

        context("when error is returned") {
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