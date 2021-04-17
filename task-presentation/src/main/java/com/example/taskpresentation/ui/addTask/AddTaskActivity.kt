package com.example.taskpresentation.ui.addTask

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            setupObserver(editText.text.toString())
        }
    }

    private fun setupObserver(text: String) {
        viewModel.insertTask(Task(text)).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        showToast(viewModel.getString(R.string.task_added))
                        openTaskList()
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(baseContext, "Error ${resource.message}", Toast.LENGTH_LONG).show()
                        Log.e("XYZ", resource.message.toString())
                    }
                }
            }
        })
    }

    override fun openTaskList() {
        val intent = Intent(this, TaskListActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
