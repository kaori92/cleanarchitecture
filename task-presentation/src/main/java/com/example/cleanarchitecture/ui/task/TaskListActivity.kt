package com.example.cleanarchitecture.ui.task

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.addTask.AddTaskActivity
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.di.component.TaskComponent
import com.example.cleanarchitecture.di.component.DaggerTaskComponent
import com.example.cleanarchitecture.di.module.TaskModule
import com.example.cleanarchitecture.domain.model.Task
import kotlinx.android.synthetic.main.activity_task_list.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class TaskListActivity : BaseActivity(), TaskListView {

    private val appComponent by lazy {
        (applicationContext as MyApplication).appComponent
    }

    private val taskComponent: TaskComponent by lazy {
        DaggerTaskComponent
            .builder()
            .taskModule(TaskModule)
            .applicationComponent(appComponent)
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): TaskListPresenter = taskComponent.presenter()

    @InjectPresenter
    lateinit var taskListPresenter: TaskListPresenter

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        progressBar = findViewById(R.id.progressBar)
        setSupportActionBar(findViewById(R.id.my_toolbar))

    }

    override fun onStart() {
        super.onStart()

        taskListPresenter.getAllTasks()
    }

    override fun setUpRecyclerView(tasks: Array<Task>) {
        val viewAdapter = TaskAdapter(tasks)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun hideLoader() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> handleAddSelected()

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun handleAddSelected(): Boolean {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivity(intent)
        return true
    }

}