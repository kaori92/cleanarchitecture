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

class TaskListActivity : BaseActivity(), TaskListView {

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

        taskListPresenter.getAllTasksLocally()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setUpRecyclerView(tasks: Array<Task>) {
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
        R.id.action_show_local -> handleShowLocalSelected()
        R.id.action_show_remote -> handleShowRemoteSelected()

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun handleAddSelected(): Boolean {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun handleShowLocalSelected(): Boolean {
        taskListPresenter.getAllTasksLocally()
        recyclerView.adapter?.notifyDataSetChanged()
        return true
    }

    private fun handleShowRemoteSelected(): Boolean {
        taskListPresenter.getAllTasksRemotely()
        recyclerView.adapter?.notifyDataSetChanged()
        return true
    }

}