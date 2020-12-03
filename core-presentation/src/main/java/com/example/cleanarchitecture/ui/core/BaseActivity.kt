package com.example.cleanarchitecture.ui.core

import android.widget.Toast
import com.example.cleanarchitecture.MyApplication
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(),
    BaseView {

    protected val appComponent by lazy {
        (applicationContext as MyApplication).appComponent
    }

    override fun showError(error: Throwable) {
        Toast.makeText(baseContext, "Error $error", Toast.LENGTH_LONG).show()
    }
}