package com.example.cleanarchitecture.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.TaskRemoteSource
import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.taskdomain.model.Task
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
class TaskRemoteSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var taskRetrofitService: TaskRetrofitService

    @Mock
    private lateinit var mapperApi: Mapper<Task, TaskApiDto>

    private lateinit var taskRemoteSource: TaskRemoteSource

    private val title = "abc"
    private val task = Task(title)
    private val taskApiDto = TaskApiDto(title)
    private val taskApiDtos = listOf(taskApiDto)
    private val tasks = listOf(task)

    @Before
    fun setUp() {
        taskRemoteSource = DefaultTaskRemoteSource(taskRetrofitService, mapperApi)
    }

    @Test
    fun testWhenInsertingTaskShouldCallTaskRetrofitServiceInsertTask() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(mapperApi.reverse(task)).thenAnswer { taskApiDto }

            taskRemoteSource.insertTask(task)

            verify(taskRetrofitService).insertTask(taskApiDto)
        }
    }

    @Test
    fun testWhenGettingTasksShouldCallTaskRetrofitGetTasks() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(taskRetrofitService.getTasks()).thenAnswer { taskApiDtos }
            Mockito.`when`(mapperApi.map(taskApiDto)).thenAnswer { task }

            val result = taskRemoteSource.getAllTasks()

            Assert.assertEquals(result, tasks)
        }
    }
}