package com.example.cleanarchitecture.ui.addTask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.domain.model.Task

import com.example.cleanarchitecture.ui.task.TaskListActivity
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

    private lateinit var editText: EditText
    private lateinit var submitLocallyButton: Button
    private lateinit var submitRemotelyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        editText = findViewById(R.id.edit_text)
        submitLocallyButton = findViewById(R.id.submit_locally_button)
        submitRemotelyButton = findViewById(R.id.submit_remotely_button)

        submitLocallyButton.setOnClickListener {
            presenter.insertTaskLocally(
                Task(
                    editText.text.toString()
                )
            )
        }

        submitRemotelyButton.setOnClickListener {
            presenter.insertTaskRemotely(
                Task(
                    editText.text.toString()
                )
            )
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

