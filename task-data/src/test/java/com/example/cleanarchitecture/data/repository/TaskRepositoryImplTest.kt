package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.time.TimeService
import com.example.cleanarchitecture.domain.model.Task
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFailsWith

class TaskRepositoryImplTest : Spek({

    val remoteSource by memoized { mock<TaskRemoteSource>() }
    val localSource by memoized { mock<TaskLocalSource>() }
    val timeService by memoized { mock<TimeService>() }

    val taskRepository by memoized {
        TaskRepositoryImpl(remoteSource, localSource, timeService)
    }

    val task = Task("abc")
    val tasks = listOf(Task("abc"))
    lateinit var testObserver: TestObserver<Void>
    lateinit var testObserverList: TestObserver<List<Task>>

    describe("inserting task") {

        context("when online") {
            beforeEachTest {
                given(localSource.insertTask(task)).willReturn(Completable.complete())

                testObserver = taskRepository.insertTask(task, true).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }

            it("should remoteSource call insertTask") {
                verify(remoteSource).insertTask(task)
            }

            it("should localSource call insertTask") {
                verify(localSource).insertTask(task)
            }
        }

        context("when offline") {
            it("should return error") {
                assertFailsWith<NoInternetException> {
                    taskRepository.insertTask(task, false)
                }
            }
        }
    }

    describe("getting tasks") {

        context("when online") {
            beforeEachTest {
                given(remoteSource.getAllTasks()).willReturn(Single.just(tasks))
                testObserverList = taskRepository.getAllTasks(true).test()
            }

            it("should return tasks") {
                testObserverList.assertResult(tasks)
            }

            it("should remoteSource call getAllTasks") {
                verify(remoteSource).getAllTasks()
            }
        }

        context("when offline") {
            context("and cache limit passed") {
                beforeEachTest {
                    given(timeService.getTime()).willReturn(5000)
                    given(timeService.getCacheTimestampMs()).willReturn(2000)
                    given(timeService.getCacheLimitMs()).willReturn(1000)
                }

                it("should return error") {
                    assertFailsWith<CachePassedException> {
                        taskRepository.getAllTasks(false)
                    }
                }
            }

            context("and cache limit NOT passed") {

                beforeEachTest {
                    given(timeService.getTime()).willReturn(2000)
                    given(timeService.getCacheTimestampMs()).willReturn(5000)
                    given(timeService.getCacheLimitMs()).willReturn(1000)

                    given(localSource.getAllTasks()).willReturn(Single.just(tasks))
                    testObserverList = taskRepository.getAllTasks(false).test()
                }

                it("should return tasks") {
                    testObserverList.assertResult(tasks)
                }

                it("should localSource call getAllTasks") {
                    verify(localSource).getAllTasks()
                }
            }
        }
    }

})