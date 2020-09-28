package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.source.LocalSource
import com.example.cleanarchitecture.data.source.RemoteSource
import com.example.cleanarchitecture.domain.model.Task
import com.nhaarman.mockitokotlin2.given
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver

class TaskRepositoryImplTest : Spek({

    val remoteSource by memoized { mock<RemoteSource>() }

    val localSource by memoized { mock<LocalSource>() }

    val taskRepository by memoized {
        TaskRepositoryImpl(remoteSource, localSource)
    }

    val task = Task("abc")
    val tasks = listOf(Task("abc"))
    val tasksSingle = Single.just(tasks)
    val error = Throwable("error")
    lateinit var testObserver: TestObserver<Void>
    lateinit var testObserverList: TestObserver<List<Task>>

    describe("inserting task locally"){
        context("when inserting task locally succeeds") {
            beforeEachTest {
                given(localSource.insertTask(task)).willReturn(Completable.complete())

                testObserver = taskRepository.insertTaskLocally(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task locally fails") {
            beforeEachTest {
                given(localSource.insertTask(task)).willReturn(Completable.error(error))

                testObserver = taskRepository.insertTaskLocally(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }

    describe("inserting task remotely"){
        context("when inserting task remotely succeeds") {
            beforeEachTest {
                given(remoteSource.insertTask(task)).willReturn(Completable.complete())

                testObserver = taskRepository.insertTaskRemotely(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task remotely fails") {
            beforeEachTest {
                given(remoteSource.insertTask(task)).willReturn(Completable.error(error))

                testObserver = taskRepository.insertTaskRemotely(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }

    describe("getting tasks locally"){
        context("when getting task locally succeeds") {
            beforeEachTest {
                given(localSource.getAllTasks()).willReturn(tasksSingle)

                testObserverList = taskRepository.getAllTasksLocally().test()
            }

            it("should completable be completed") {
                testObserverList.assertResult(tasks)
            }
        }

        context("when getting task locally fails") {
            beforeEachTest {
                given(localSource.getAllTasks()).willReturn(Single.error(error))

                testObserverList = taskRepository.getAllTasksLocally().test()
            }

            it("should return error") {
                testObserverList.assertError(error)
            }
        }
    }

    describe("getting tasks remotely"){
        context("when getting task remotely succeeds") {
            beforeEachTest {
                given(remoteSource.getAllTasks()).willReturn(tasksSingle)

                testObserverList = taskRepository.getAllTasksRemotely().test()
            }

            it("should completable be completed") {
                testObserverList.assertResult(tasks)
            }
        }

        context("when getting task remotely fails") {
            beforeEachTest {
                given(remoteSource.getAllTasks()).willReturn(Single.error(error))

                testObserverList = taskRepository.getAllTasksRemotely().test()
            }

            it("should return error") {
                testObserverList.assertError(error)
            }
        }
    }
})