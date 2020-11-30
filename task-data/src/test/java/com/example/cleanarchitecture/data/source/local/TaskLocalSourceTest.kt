package com.example.cleanarchitecture.data.source.local

import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.local.dao.TaskDao
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.domain.model.Task
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskLocalSourceTest : Spek({
    val title = "abc"
    val task = Task(title)
    val taskDbEntity = TaskDbEntity(title)
    val tasks = listOf(task)
    val taskDbEntities = listOf(taskDbEntity)

    val taskDao: TaskDao by memoized {
        mock<TaskDao>()
    }
    val mapperDb: Mapper<Task, TaskDbEntity> by memoized {
        mock<Mapper<Task, TaskDbEntity>>()
    }

    val taskLocalSource: TaskLocalSource by memoized {
        DefaultTaskLocalSource(taskDao, mapperDb)
    }

    lateinit var testObserver: TestObserver<Void>
    lateinit var testListObserver: TestObserver<List<Task>>
    val error = Throwable("error")

    describe("inserting task") {
        context("when inserting task"){
            beforeEachTest {
                given(mapperDb.reverse(task)).willReturn(taskDbEntity)

                taskLocalSource.insertTask(task)
            }

            it("should taskDao be called with insertTask") {
                verify(taskDao).insertTask(taskDbEntity)
            }
        }

        context("when inserting task"){
            beforeEachTest {
                taskLocalSource.insertTask(task)
            }

            it("should mapperDb be called with reverse") {
                verify(mapperDb).reverse(task)
            }
        }

        context("when inserting task succeeds"){

            beforeEachTest {
                given(taskDao.insertTask(taskDbEntity)).willReturn(Completable.complete())
                given(mapperDb.reverse(task)).willReturn(taskDbEntity)

                testObserver = taskLocalSource.insertTask(task).test()
            }

            it("should completable be completed") {
                testObserver.assertComplete()
            }
        }

        context("when inserting task fails"){

                beforeEachTest {
                    given(taskDao.insertTask(taskDbEntity)).willReturn(Completable.error(error))
                    given(mapperDb.reverse(task)).willReturn(taskDbEntity)

                    testObserver = taskLocalSource.insertTask(task).test()
                }

                it("should return error") {
                    testObserver.assertError(error)
                }
            }
    }

    describe("getting all tasks") {
        context("getting all tasks"){
            beforeEachTest {
                taskLocalSource.getAllTasks()
            }

            it("should taskDao be called with getAllTasks") {
                verify(taskDao).getAllTasks()
            }
        }

        context("getting all tasks"){
            beforeEachTest {
                given(taskDao.getAllTasks()).willReturn(Single.just(taskDbEntities))
                taskLocalSource.getAllTasks()
            }
// TODO fix
            it("should mapperDb be called with map") {
                verify(mapperDb).map(taskDbEntities)
            }
        }

        context("when getting all tasks succeeds"){

            beforeEachTest {
                given(taskDao.getAllTasks()).willReturn(Single.just(taskDbEntities))
                given(mapperDb.map(taskDbEntities)).willReturn(tasks)

                testListObserver = taskLocalSource.getAllTasks().test()
            }

            it("should completable be completed") {
                testListObserver.assertComplete()
            }
        }

        context("when getting all tasks fails"){

            beforeEachTest {
                given(taskDao.getAllTasks()).willReturn(Single.error(error))
                given(mapperDb.map(taskDbEntities)).willReturn(tasks)

                testListObserver = taskLocalSource.getAllTasks().test()
            }

            it("should return error") {
                testListObserver.assertError(error)
            }
        }
    }
})