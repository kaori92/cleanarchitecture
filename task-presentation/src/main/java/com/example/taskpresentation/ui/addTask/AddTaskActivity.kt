package com.example.taskpresentation.ui.addTask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.corepresentation.ui.core.BaseActivity
import com.example.taskpresentation.di.module.AddTaskModule
import com.example.taskpresentation.di.component.AddTaskComponent
import com.example.taskdomain.model.Task
import com.example.taskpresentation.R
import com.example.taskpresentation.di.component.DaggerAddTaskComponent
import com.example.taskpresentation.ui.task.TaskListActivity
import com.example.cleanarchitecture.Resource
import com.example.taskpresentation.viewmodel.addTask.AddTaskViewModel

class AddTaskActivity : BaseActivity(),
    AddTaskView {

    private lateinit var viewModel: AddTaskViewModel

    private val component: AddTaskComponent by lazy {
        DaggerAddTaskComponent
            .builder()
            .addTaskModule(AddTaskModule)
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = component.viewModel()

        setContentView(R.layout.activity_add_task)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val editText: EditText = findViewById(R.id.edit_text)
        val submitButton: Button = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            viewModel.insertTask(
                Task(
                    editText.text.toString()
                )
            )

            if(viewModel.taskResource?.status == Resource.Status.ERROR) {
                viewModel.taskResource?.message?.let { errorMessage ->
                    Toast.makeText(baseContext, "Error $errorMessage", Toast.LENGTH_LONG).show()
                }
            } else {
                showToast(
                    viewModel.getString(R.string.task_added)
                )
                openTaskList()
            }
        }
    }

    override fun openTaskList() {
        val intent = Intent(this, TaskListActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
