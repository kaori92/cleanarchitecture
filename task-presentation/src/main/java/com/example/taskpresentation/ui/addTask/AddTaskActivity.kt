package com.example.taskpresentation.ui.addTask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.setContent
import com.example.corepresentation.ui.core.BaseActivity
import com.example.taskpresentation.di.module.AddTaskModule
import com.example.taskpresentation.di.component.AddTaskComponent
import com.example.taskdomain.model.Task
import com.example.taskpresentation.R
import com.example.taskpresentation.ui.task.TaskListActivity
import com.example.cleanarchitecture.Resource
import com.example.taskpresentation.di.component.DaggerAddTaskComponent
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModel
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModelFactory

class AddTaskActivity : BaseActivity(),
    AddTaskView {

//    val viewModel: AddTaskViewModel by viewModels { AddTaskViewModelFactory("my awesome param") }

    private val component: AddTaskComponent by lazy {
        DaggerAddTaskComponent
            .builder()
            .addTaskModule(AddTaskModule)
            .applicationComponent(appComponent)
            .build()
    }

    lateinit var viewModelFactory: AddTaskViewModelFactory
    lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = component.factory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)

        val query = viewModel.query.value

//        setContent {
//            ScrollableColumn {
//                // BaseTextField is a composable that is used to take input. It is similar to EditText.
//                // onValueChange will be called when there is a change in content of text field.
//                TextField(
//                    value = query,
//                    onValueChange = { viewModel.onQueryChanged(it) },
//                    placeholder = { androidx.compose.foundation.Text(text = "Enter task title") },
//                    maxLines = 5
//                )
//            }
//            TextField(
//                value = query,
//                onValueChange = { newValue -> viewModel.onQueryChanged(newValue) },
//                placeholder = { Text(text = "Enter task title") },
//                maxLines = 5
//            )
//            Spacer(modifier = Modifier.padding(16.dp))
//
//            Button(
//                onClick = {
//                    setupObserver(query)
//                },
//                content = {
//                    Text(stringResource(R.string.add))
//                }
//            )
//        }
    }

//    private fun setupObserver(text: String) {
//        viewModel.insertTask(Task(text)).observe(this, {
//            it?.let { resource ->
//                when (resource.status) {
//                    Resource.Status.SUCCESS -> {
//                        showToast(viewModel.getString(R.string.task_added))
//                        openTaskList()
//                    }
//                    Resource.Status.ERROR -> {
//                        Toast.makeText(baseContext, "Error ${resource.message}", Toast.LENGTH_LONG).show()
//                        Log.e("XYZ", resource.message.toString())
//                    }
//                }
//            }
//        })
//    }

    override fun openTaskList() {
        val intent = Intent(this, TaskListActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}