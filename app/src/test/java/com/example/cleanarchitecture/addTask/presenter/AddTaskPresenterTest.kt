package com.example.cleanarchitecture.addTask.presenter

import com.example.cleanarchitecture.addTask.models.AddTaskModel
import com.example.cleanarchitecture.addTask.view.AddTaskView
import com.example.cleanarchitecture.core.StringService
import com.example.cleanarchitecture.core.TestSchedulerProvider
import com.example.cleanarchitecture.datasource.data.Task
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AddTaskPresenterTest : Spek({
    val schedulerProvider = TestSchedulerProvider
    val model: AddTaskModel by memoized { mock<AddTaskModel>() }
    val view: AddTaskView by memoized { mock<AddTaskView>() }

    val stringService by memoized { mock<StringService>() }
    val presenter: AddTaskPresenter by memoized {
        AddTaskPresenter(model, schedulerProvider, stringService)
    }

    describe("adding tasks") {
        val task = Task("xyz")

        context("when presenter inserts task") {

            beforeEachTest {
                given(model.insertOrUpdateTask(task)).willReturn(Completable.complete())

                presenter.attachView(view)
                presenter.insertOrUpdateTask(task)
            }

            it("should call view openTaskList") {
                verify(view).openTaskList()
            }
        }

        context("when error is returned") {
            val error = Throwable("error")

            beforeEachTest {
                given(model.insertOrUpdateTask(task)).willReturn(Completable.error(error))

                presenter.attachView(view)
                presenter.insertOrUpdateTask(task)
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }
})