package com.example.taskdomain.interactor

import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.time.TimeService
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetTasksUseCaseTest : Spek({
    val taskRepository: TaskRepository by memoized { mock<TaskRepository>() }
    val connectivityChecker: ConnectivityChecker by memoized { mock<ConnectivityChecker>() }
    val timeService: TimeService by memoized { mock<TimeService>() }

    val getTasksUseCase: GetTasksUseCase by memoized {
        GetTasksUseCaseImpl(
            taskRepository,
            connectivityChecker,
            timeService
        )
    }

    val tasks = listOf(Task("x"))
    val isOnline = true

    describe("get all tasks") {
        var result = listOf<Task>()

        context("when getting all tasks succeeds") {

            beforeEachTest {
                given(connectivityChecker.isOnline()).willReturn(isOnline)
                given(taskRepository.getAllTasks(isOnline)).willReturn(tasks)

                result = getTasksUseCase.execute()
            }

            it("should return correct tasks") {
                Assert.assertEquals(result, tasks)
            }

            it("should update cache timestamp") {
                verify(timeService).updateCacheTimestampMs()
            }
        }
    }
})