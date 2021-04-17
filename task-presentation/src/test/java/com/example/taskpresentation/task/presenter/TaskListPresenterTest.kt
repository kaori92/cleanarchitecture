package com.example.taskpresentation.task.presenter

import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.example.cleanarchitecture.scheduler.TestSchedulerProvider
import com.example.taskpresentation.ui.task.TaskListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class TaskListPresenterTest : Spek({
    val getTasksUseCase: GetTasksUseCase by memoized { mock<GetTasksUseCase>() }
    val schedulerProvider = TestSchedulerProvider
    val view: TaskListView by memoized { mock<TaskListView>() }

    val presenter: TaskListPresenter by memoized {
        TaskListPresenter(
            getTasksUseCase,
            schedulerProvider
        )
    }
    val tasks = listOf(Task("X"))
    val error = Throwable("error")

    describe("getting tasks") {

        context("when presenter gets tasks") {
            beforeEachTest {
                given(getTasksUseCase.execute()).willReturn(Single.just(tasks))

                presenter.attachView(view)
                presenter.getAllTasks()
            }

            it("should call view setUpRecyclerView") {
                verify(view).setUpRecyclerView(tasks.toTypedArray())
            }

            it("should call showLoader") {
                verify(view).showLoader()
            }

            it("should hide loader") {
                verify(view).hideLoader()
            }

        }

        context("when error is returned") {
            beforeEachTest {
                given(getTasksUseCase.execute()).willReturn(Single.error(error))

                presenter.attachView(view)
                presenter.getAllTasks()
            }

            it("should call showLoader") {
                verify(view).showLoader()
            }

            it("should show error") {
                verify(view).showError(any())
            }

            it("should hide loader") {
                verify(view).hideLoader()
            }
        }

    }

})