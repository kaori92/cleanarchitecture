package com.example.cleanarchitecture.data.source.remote

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.cleanarchitecture.domain.model.Task
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskRemoteSourceTest : Spek({
    lateinit var testObserver: TestObserver<Void>

    lateinit var testListObserver: TestObserver<List<Task>>

    val taskRetrofitService by memoized {
        mock<TaskRetrofitService>()
    }

    val mapperApi by memoized {
        mock<Mapper<Task, TaskApiDto>>()
    }

    val taskRemoteSource by memoized {
        TaskRemoteSource(taskRetrofitService, mapperApi)
    }

    val title = "abc"
    val error = Throwable("error")
    val task = Task(title)
    val taskApiDto = TaskApiDto(title)
    val taskApiDtos = listOf(taskApiDto)
    val tasks = listOf(task)
    val taskListSingle = Single.just(taskApiDtos)

    describe("inserting task") {

        context("when inserting task succeeds"){
            beforeEachTest {
                given(taskRetrofitService.insertTask(taskApiDto)).willReturn(Completable.complete())
                given(mapperApi.reverse(task)).willReturn(taskApiDto)

                testObserver = taskRemoteSource.insertTask(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task fails"){

            beforeEachTest {
                given(taskRetrofitService.insertTask(taskApiDto)).willReturn(Completable.error(error))
                given(mapperApi.reverse(task)).willReturn(taskApiDto)

                testObserver = taskRemoteSource.insertTask(task).test()
            }

            it("should return error") {
                testObserver.assertError(error)
            }
        }
    }

    describe("getting tasks") {

        context("when getting tasks succeeds"){

            beforeEachTest {
                given(taskRetrofitService.getTasks()).willReturn(taskListSingle)
                given(mapperApi.map(taskApiDtos)).willReturn(tasks)

                testListObserver = taskRemoteSource.getAllTasks().test()
            }

            it("should completable be completed") {
                testListObserver.assertComplete()
            }
        }

        context("when getting tasks fails"){

            beforeEachTest {
                given(taskRetrofitService.getTasks()).willReturn(Single.error(error))
                given(mapperApi.map(taskApiDtos)).willReturn(tasks)

                testListObserver = taskRemoteSource.getAllTasks().test()
            }

            it("should return error") {
                testListObserver.assertError(error)
            }
        }
    }
})