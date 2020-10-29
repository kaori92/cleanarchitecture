package com.example.cleanarchitecture.addTask.presenter

import com.example.cleanarchitecture.domain.interactor.definition.AddTaskModel
import com.example.cleanarchitecture.ui.addTask.AddTaskView
import com.example.cleanarchitecture.scheduler.TestSchedulerProvider
import com.example.cleanarchitecture.domain.model.Task
import com.example.cleanarchitecture.ui.addTask.AddTaskPresenter
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

    val presenter: AddTaskPresenter by memoized {
        AddTaskPresenter(
            model,
            schedulerProvider
        )
    }

    val task = Task("xyz")
    val error = Throwable("error")

    describe("inserting tasks") {
        context("when presenter inserts task") {

            beforeEachTest {
                given(model.insertTask(task)).willReturn(Completable.complete())

                presenter.attachView(view)
                presenter.insertTask(task)
            }

            it("should call view openTaskList") {
                verify(view).openTaskList()
            }
        }

        context("when error is returned") {
            beforeEachTest {
                given(model.insertTask(task)).willReturn(Completable.error(error))

                presenter.attachView(view)
                presenter.insertTask(task)
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }

})