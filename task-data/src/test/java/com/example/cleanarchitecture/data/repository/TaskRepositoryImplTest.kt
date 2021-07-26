package com.example.cleanarchitecture.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.cleanarchitecture.data.exception.CachePassedException
import com.example.cleanarchitecture.data.exception.NoInternetException
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.time.TimeService
import com.example.taskdomain.model.Task
import com.example.taskdomain.repository.TaskRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class TaskRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remoteSource: TaskRemoteSource
    @Mock
    private lateinit var localSource: TaskLocalSource
    @Mock
    private lateinit var timeService: TimeService

    private lateinit var taskRepository: TaskRepository

    private val task = Task("abc")

    @Before
    fun setUp() {
        taskRepository = TaskRepositoryImpl(remoteSource, localSource, timeService)
    }

    @Test
    fun testWhenInsertingTaskOnlineShouldCallLocalSourceInsertTask() {
        testCoroutineRule.runBlockingTest {
            taskRepository.insertTask(task, true)

            verify(localSource).insertTask(task)
        }
    }

    @Test
    fun testWhenInsertingTaskOnlineShouldCallRemoteSourceInsertTask() {
        testCoroutineRule.runBlockingTest {
            taskRepository.insertTask(task, true)

            verify(remoteSource).insertTask(task)
        }
    }

    @Test(expected = NoInternetException::class)
    fun testWhenInsertingTaskOfflineShouldThrowNoInternetException() {
        testCoroutineRule.runBlockingTest {
            taskRepository.insertTask(task, false)
        }
    }

    @Test
    fun testWhenGettingTasksOnlineShouldCallRemoteSourceGetTasks() {
        testCoroutineRule.runBlockingTest {
            taskRepository.getAllTasks(true)

            verify(remoteSource).getAllTasks()
        }
    }

    @Test(expected = CachePassedException::class)
    fun testWhenGettingTasksOfflineAndTimeoutExceededShouldThrowCachePassedException() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(timeService.isTimeoutExceeded()).thenAnswer { true }

            taskRepository.getAllTasks(false)
        }
    }

    @Test
    fun testWhenGettingTasksOfflineAndNoTimeoutShouldGetLocalTasks() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(timeService.isTimeoutExceeded()).thenAnswer { false }

            taskRepository.getAllTasks(false)

            verify(localSource).getAllTasks()
        }
    }
}