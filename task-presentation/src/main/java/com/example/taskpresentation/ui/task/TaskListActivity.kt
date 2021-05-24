package com.example.taskpresentation.ui.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskpresentation.ui.addTask.AddTaskActivity
import com.example.corepresentation.ui.core.BaseActivity
import com.example.taskpresentation.di.component.TaskComponent
import com.example.taskpresentation.di.module.TaskModule
import com.example.taskdomain.model.Task
import com.example.taskpresentation.R
import com.example.taskpresentation.di.component.DaggerTaskComponent
import com.example.taskpresentation.viewmodel.task.TaskListViewAction
import com.example.taskpresentation.viewmodel.task.TaskListViewModel
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : BaseActivity(), TaskListView {

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TaskListViewModel

    private val component: TaskComponent by lazy {
        DaggerTaskComponent
            .builder()
            .taskModule(TaskModule)
            .applicationComponent(appComponent)
            .build()
    }

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = component.viewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TaskListViewModel::class.java)

        setContentView(R.layout.activity_task_list)

        progressBar = findViewById(R.id.progressBar)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.loadAllTasks()
        viewModel.getViewAction()
            .observe(this, { viewAction ->
                when (viewAction) {
                    is TaskListViewAction.ShowTasks -> {
                        hideLoader()
                        setUpRecyclerView(viewAction.tasks.toTypedArray())
                    }
                    is TaskListViewAction.ShowErrorMessage -> {
                        hideLoader()
                        Toast.makeText(this, viewAction.message, Toast.LENGTH_LONG).show()
                        Log.e("XYZ", viewAction.message)
                    }
                    is TaskListViewAction.ShowLoading -> {
                        showLoader()
                    }
                }
            })
    }

    private fun setUpRecyclerView(tasks: Array<Task>) {
        val viewAdapter = TaskAdapter(tasks)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun hideLoader() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showLoader() {
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