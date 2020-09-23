package com.example.cleanarchitecture.ui.task

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.addTask.AddTaskActivity
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.domain.model.Task
import kotlinx.android.synthetic.main.activity_task_list.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class TaskListActivity : BaseActivity(),
    TaskListView {

    private val component by lazy {
        (applicationContext as MyApplication).appComponent
            .requestTaskComponentBuilder()
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): TaskListPresenter = component.presenter()

    @InjectPresenter
    lateinit var taskListPresenter: TaskListPresenter

    private lateinit var viewAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    override fun onResume() {
        super.onResume()

        taskListPresenter.getAllTasks()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun refresh() {
        taskListPresenter.getAllTasks()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setUpRecyclerView(tasks: Array<com.example.cleanarchitecture.domain.model.Task>) {
        viewAdapter = TaskAdapter(tasks)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        recyclerView.adapter?.notifyDataSetChanged()
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