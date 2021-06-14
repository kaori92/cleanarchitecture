package com.example.taskpresentation.viewmodel.addTask

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.TestCoroutineRule
import com.example.taskdomain.interactor.definition.GetStringResourceUseCase
import com.example.taskdomain.interactor.definition.InsertTaskUseCase
import com.example.taskdomain.model.Task
import com.example.taskpresentation.viewmodel.TaskExecutorWithIdlingResourceRule
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Thread.sleep

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddTaskViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var insertTaskUseCase: InsertTaskUseCase

    @Mock
    private lateinit var getStringUseCase: GetStringResourceUseCase

    @Mock
    private lateinit var observer: Observer<AddTaskViewAction>

    private lateinit var viewModel: AddTaskViewModel
    private val task = Task("x")

    @Before
    fun setUp() {
        viewModel = AddTaskViewModel(insertTaskUseCase, getStringUseCase)

        viewModel.viewAction.observeForever(observer)
    }

    private fun prepareForInsertTasksSuccess() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(insertTaskUseCase.execute(task)).thenAnswer { }
        }
    }

    @Test
    fun testViewModelInsertTaskShouldExecuteUseCaseWithSameTask() {
        testCoroutineRule.runBlockingTest {
            prepareForInsertTasksSuccess()

            viewModel.insertTask(task)
            verify(insertTaskUseCase).execute(task)
        }
    }

    @Test
    fun testViewModelInsertTaskShouldPostValueShowSuccess() {
        val viewAction = AddTaskViewAction.ShowSuccessMessage

        testCoroutineRule.runBlockingTest {
            prepareForInsertTasksSuccess()

            viewModel.insertTask(task)

            sleep(10) // sometimes fails without

            assertEquals(viewAction, viewModel.viewAction.value)
        }
    }

    @Test
    fun testViewModelInsertTaskShouldShowError() {
        testCoroutineRule.runBlockingTest {
            val message = "Error!"
            val exception = Exception(message)
            viewModel.viewAction.observeForever(observer)
            Mockito.`when`(insertTaskUseCase.execute(task)).thenAnswer {
                throw exception
            }

            viewModel.insertTask(task)
            sleep(10) // sometimes fails without

            verify(observer).onChanged(AddTaskViewAction.ShowErrorMessage("Error occurred inserting task $task: $message"))
        }
    }

    @After
    fun tearDown() {
        viewModel.viewAction.removeObserver(observer)
    }
}