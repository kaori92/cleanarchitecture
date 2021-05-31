package com.example.taskdomain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.cleanarchitecture.connectivity.ConnectivityChecker
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
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
class InsertTaskUseCaseTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var taskRepository: TaskRepository
    @Mock
    private lateinit var connectivityChecker: ConnectivityChecker

    private lateinit var useCase: InsertTaskUseCase
    private val task = Task("abc")
    private val isOnline = true

    @Before
    fun setUp() {
        useCase = InsertTaskUseCaseImpl(taskRepository, connectivityChecker)
    }

    @Test
    fun testShouldCallRepositoryInsertTaskWhenCallingExecute() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(connectivityChecker.isOnline()).thenAnswer { isOnline }

            useCase.execute(task)

            verify(taskRepository).insertTask(task, isOnline)
        }
    }
}