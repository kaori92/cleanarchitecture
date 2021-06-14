package com.example.taskdomain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.cleanarchitecture.data.time.TimeService
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetTasksUseCaseTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var taskRepository: TaskRepository

    @Mock
    private lateinit var connectivityChecker: ConnectivityChecker

    @Mock
    private lateinit var timeService: TimeService

    private lateinit var getTasksUseCase: GetTasksUseCase

    private val tasks = listOf(Task("x"))
    private val isOnline = true

    @Before
    fun setUp() {
        getTasksUseCase = GetTasksUseCaseImpl(
            taskRepository,
            connectivityChecker,
            timeService
        )
    }

    @Test
    fun testShouldReturnCorrectTasksWhenGettingAllTasks() {
        var result: List<Task>
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(connectivityChecker.isOnline()).thenAnswer { isOnline }
            Mockito.`when`(taskRepository.getAllTasks(isOnline)).thenAnswer { tasks }

            result = getTasksUseCase.execute()

            Assert.assertEquals(result, tasks)
        }
    }

    @Test
    fun testShouldUpdateCacheTimestampWhenGettingAllTasks() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(connectivityChecker.isOnline()).thenAnswer { isOnline }
            Mockito.`when`(taskRepository.getAllTasks(isOnline)).thenAnswer { tasks }

            getTasksUseCase.execute()

            verify(timeService).updateCacheTimestampMs()
        }
    }
}