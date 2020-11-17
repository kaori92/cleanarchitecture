package com.example.cleanarchitecture.ui.notification

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.MyApplication
import com.example.cleanarchitecture.di.component.NotificationComponent
import com.example.cleanarchitecture.di.component.DaggerNotificationComponent
import com.example.cleanarchitecture.di.module.NotificationModule
import com.example.cleanarchitecture.domain.model.MyNotification
import com.example.cleanarchitecture.ui.addNotification.AddNotificationActivity
import kotlinx.android.synthetic.main.activity_notification_list.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NotificationListActivity : BaseActivity(), NotificationListView {

    private val appComponent by lazy {
        (applicationContext as MyApplication).appComponent
    }

    private val notificationComponent: NotificationComponent by lazy {
        DaggerNotificationComponent
            .builder()
            .notificationModule(NotificationModule)
            .applicationComponent(appComponent)
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): NotificationListPresenter = notificationComponent.presenter()

    @InjectPresenter
    lateinit var notificationListPresenter: NotificationListPresenter

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)

        progressBar = findViewById(R.id.progressBar)
        setSupportActionBar(findViewById(R.id.my_toolbar))

    }

    override fun onStart() {
        super.onStart()

        notificationListPresenter.getAllNotifications()
    }

    override fun setUpRecyclerView(notifications: Array<MyNotification>) {
        val viewAdapter = NotificationAdapter(notifications)

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
        val intent = Intent(this, AddNotificationActivity::class.java)
        startActivity(intent)
        return true
    }

}