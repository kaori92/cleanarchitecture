package com.example.notificationpresentation.ui.addNotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.corepresentation.ui.core.BaseActivity
import com.example.cleanarchitecture.notificationdata.CHANNEL_ID
import com.example.cleanarchitecture.notificationdata.NOTIFICATION_ID
import com.example.cleanarchitecture.notificationdata.NOTIFICATION_TIMEOUT_MS
import com.example.notificationpresentation.di.module.AddNotificationModule
import com.example.notificationpresentation.di.component.AddNotificationComponent
import com.example.notificationdomain.model.MyNotification
import com.example.notificationpresentation.R
import com.example.notificationpresentation.di.component.DaggerAddNotificationComponent

import com.example.notificationpresentation.ui.notification.NotificationListActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AddNotificationActivity : BaseActivity(),
    AddNotificationView {

    private val component: AddNotificationComponent by lazy {
        DaggerAddNotificationComponent
            .builder()
            .addNotificationModule(AddNotificationModule)
            .applicationComponent(appComponent)
            .build()
    }

    @ProvidePresenter
    fun providePresenter(): AddNotificationPresenter = component.presenter()

    @InjectPresenter
    lateinit var presenter: AddNotificationPresenter

    private lateinit var editText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notification)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        editText = findViewById(R.id.edit_text)
        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            presenter.insertNotification(
                MyNotification(
                    editText.text.toString()
                )
            )
        }

    }

    override fun openNotificationList() {
        val intent = Intent(this, NotificationListActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun showNotification(notification: MyNotification) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(notification.title)
            .setSmallIcon(android.R.drawable.btn_star_big_on)
            .setTimeoutAfter(NOTIFICATION_TIMEOUT_MS)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        createNotificationChannel(builder)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel(builder: NotificationCompat.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(CHANNEL_ID)
        }
    }

}

