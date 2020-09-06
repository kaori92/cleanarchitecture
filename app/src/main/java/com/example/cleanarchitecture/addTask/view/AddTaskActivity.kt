package com.example.cleanarchitecture.addTask.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.addTask.presenter.AddTaskPresenter
import com.example.cleanarchitecture.core.BaseActivity
import com.example.cleanarchitecture.core.MyApplication
import com.example.cleanarchitecture.datasource.data.Task

import com.example.cleanarchitecture.task.view.TaskListActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AddTaskActivity : BaseActivity(),
    AddTaskView {

    private val component by lazy {
        (applicationContext as MyApplication).appComponent
            .requestAddTaskComponentBuilder()
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): AddTaskPresenter = component.presenter()

    @InjectPresenter
    lateinit var presenter: AddTaskPresenter

    lateinit var editText: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        editText = findViewById(R.id.edit_text)
        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            presenter.insertOrUpdateTask(Task(editText.text.toString()))
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

