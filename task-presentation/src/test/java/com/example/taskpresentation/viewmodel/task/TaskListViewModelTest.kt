package com.example.taskpresentation.viewmodel.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.taskdomain.interactor.definition.GetTasksUseCase
import com.example.taskdomain.model.Task
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TaskListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getTasksUseCase: GetTasksUseCase

    @Mock
    private lateinit var observer: Observer<TaskListViewAction>

    private lateinit var viewModel: TaskListViewModel
    private val tasks = listOf(Task("X"))

    @Before
    fun setUp() {
        viewModel = TaskListViewModel(getTasksUseCase)
    }

    private fun prepareForGetTasksSuccess() {
        testCoroutineRule.runBlockingTest {
            viewModel.getViewAction().observeForever(observer)
            whenever(getTasksUseCase.execute()).thenAnswer { tasks }
        }
    }

    @Test
    fun testViewModelGetTasksShouldShowLoading() {
        testCoroutineRule.runBlockingTest {
            prepareForGetTasksSuccess()

            viewModel.loadAllTasks()

            verify(observer).onChanged(TaskListViewAction.ShowLoading)
        }
    }

    @Test
    fun testViewModelGetTasksShouldShowTasksSuccessfully() {
        testCoroutineRule.runBlockingTest {
            prepareForGetTasksSuccess()

            viewModel.loadAllTasks()

            verify(observer).onChanged(TaskListViewAction.ShowTasks(tasks))
        }
    }

    @Test
    fun testViewModelGetTasksShouldShowError() {
        testCoroutineRule.runBlockingTest {
            val message = "Error!"
            val exception = Exception(message)
            viewModel.getViewAction().observeForever(observer)
            whenever(getTasksUseCase.execute()).thenAnswer {
                throw exception
            }

            viewModel.loadAllTasks()

            verify(observer).onChanged(TaskListViewAction.ShowErrorMessage("Error Occurred getting tasks: $message"))
        }
    }

    @After
    fun tearDown() {
        viewModel.getViewAction().removeObserver(observer)
    }
}
