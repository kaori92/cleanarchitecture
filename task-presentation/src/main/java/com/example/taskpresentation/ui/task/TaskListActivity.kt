package com.example.taskpresentation.ui.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskpresentation.ui.addTask.AddTaskActivity
import com.example.corepresentation.ui.core.BaseActivity
import com.example.taskpresentation.di.component.TaskComponent
import com.example.taskpresentation.di.module.TaskModule
import com.example.taskdomain.model.Task
import com.example.taskpresentation.R
import com.example.taskpresentation.di.component.DaggerTaskComponent
import com.example.cleanarchitecture.Resource
import com.example.taskpresentation.viewmodel.task.TaskListViewModel
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : BaseActivity(), TaskListView {

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
        viewModel = component.viewModel()
        setContentView(R.layout.activity_task_list)

        progressBar = findViewById(R.id.progressBar)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getAllTasks().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let { tasks -> setUpRecyclerView(tasks.toTypedArray()) }
                    }
                    Resource.Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        println(it.message.toString())
                        Log.e("XYZ", it.message.toString())
                    }
                    Resource.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
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