package com.example.cleanarchitecture.ui.landingpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.di.component.DaggerLandingPageComponent
import com.example.cleanarchitecture.di.component.LandingPageComponent
import com.example.cleanarchitecture.di.module.LandingPageModule
import com.example.cleanarchitecture.ui.core.BaseActivity
import com.example.cleanarchitecture.ui.notification.NotificationListActivity
import com.example.cleanarchitecture.ui.task.TaskListActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class LandingPageActivity : BaseActivity(), LandingPageView {

    private lateinit var tasksButton: Button
    private lateinit var notificationsButton: Button

    private val component: LandingPageComponent by lazy {
        DaggerLandingPageComponent
            .builder()
            .landingPageModule(LandingPageModule)
            .applicationComponent(appComponent)
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): LandingPagePresenter = component.presenter()

    @InjectPresenter
    lateinit var presenter: LandingPagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        tasksButton = findViewById(R.id.task_button)
        notificationsButton = findViewById(R.id.notification_button)

        tasksButton.setOnClickListener {
            val intent = Intent(this, TaskListActivity::class.java)
            startActivity(intent)
        }

        notificationsButton.setOnClickListener {
            val intent = Intent(this, NotificationListActivity::class.java)
            startActivity(intent)
        }
    }
}