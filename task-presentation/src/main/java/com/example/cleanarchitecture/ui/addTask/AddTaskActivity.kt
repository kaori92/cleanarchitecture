package com.example.cleanarchitecture.ui.addTask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.di.module.AddTaskModule
import com.example.cleanarchitecture.di.component.AddTaskComponent
import com.example.cleanarchitecture.di.component.DaggerAddTaskComponent
import com.example.cleanarchitecture.domain.model.Task

import com.example.cleanarchitecture.ui.task.TaskListActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AddTaskActivity : BaseActivity(),
    AddTaskView {

    private val appComponent by lazy {
        (applicationContext as MyApplication).appComponent
    }

    private val component: AddTaskComponent by lazy {
        DaggerAddTaskComponent
            .builder()
            .addTaskModule(AddTaskModule)
            .applicationComponent(appComponent)
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): AddTaskPresenter = component.presenter()

    @InjectPresenter
    lateinit var presenter: AddTaskPresenter

    private lateinit var editText: EditText
    private lateinit var submitButton: Button

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
            presenter.insertTask(
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

