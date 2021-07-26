package com.example.cleanarchitecture.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.cleanarchitecture.data.mapper.base.Mapper
import com.example.cleanarchitecture.data.source.TaskLocalSource
import com.example.cleanarchitecture.data.source.local.dao.TaskDao
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
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
class TaskLocalSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val title = "abc"
    private val task = Task(title)
    private val taskDbEntity = TaskDbEntity(title)
    private val tasks = listOf(task)
    private val taskDbEntities = listOf(taskDbEntity)

    @Mock
    private lateinit var taskDao: TaskDao
    @Mock
    private lateinit var mapperDb: Mapper<Task, TaskDbEntity>
    private lateinit var taskLocalSource: TaskLocalSource

    @Before
    fun setUp() {
        taskLocalSource = DefaultTaskLocalSource(taskDao, mapperDb)
    }

    @Test
    fun testWhenInsertingTaskShouldCallTaskDaoInsertTask() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(mapperDb.reverse(task)).thenAnswer { taskDbEntity }

            taskLocalSource.insertTask(task)

            verify(taskDao).insertTask(taskDbEntity)
        }
    }

    @Test
    fun testWhenGettingTasksShouldCallTaskDaoGetTasks() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(mapperDb.map(taskDbEntity)).thenAnswer { task }
            Mockito.`when`(taskDao.getAllTasks()).thenAnswer { taskDbEntities }

            val result = taskLocalSource.getAllTasks()

            Assert.assertEquals(result, tasks)
        }
    }
}