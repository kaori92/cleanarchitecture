package com.example.cleanarchitecture.addTask.presenter

import com.example.cleanarchitecture.domain.interactor.definition.GetStringResourceUseCase
import com.example.cleanarchitecture.domain.interactor.definition.InsertTaskUseCase
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
    val insertTaskUseCase: InsertTaskUseCase by memoized { mock<InsertTaskUseCase>() }
    val getStringUseCase: GetStringResourceUseCase by memoized { mock<GetStringResourceUseCase>() }
    val view: AddTaskView by memoized { mock<AddTaskView>() }

    val presenter: AddTaskPresenter by memoized {
        AddTaskPresenter(
            insertTaskUseCase,
            getStringUseCase,
            schedulerProvider
        )
    }

    val task = Task("xyz")
    val error = Throwable("error")
    val message = "task added"

    describe("inserting tasks") {
        context("when presenter inserts task") {
            beforeEachTest {
                given(getStringUseCase.execute(any())).willReturn(message)
                given(insertTaskUseCase.execute(task)).willReturn(Completable.complete())

                presenter.attachView(view)
                presenter.insertTask(task)
            }

            it("should call view openTaskList") {
                verify(view).openTaskList()
            }

            it("should call view showToast") {
                verify(view).showToast(message)
            }
        }

        context("when error is returned") {
            beforeEachTest {
                given(insertTaskUseCase.execute(task)).willReturn(Completable.error(error))

                presenter.attachView(view)
                presenter.insertTask(task)
            }

            it("should show error") {
                verify(view).showError(any())
            }
        }
    }

})