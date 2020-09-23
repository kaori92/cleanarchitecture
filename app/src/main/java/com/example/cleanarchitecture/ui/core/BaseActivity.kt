package com.example.cleanarchitecture.ui.core

import android.widget.Toast
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(),
    BaseView {

    override fun showError(error: Throwable) {
        Toast.makeText(baseContext, "Error $error", Toast.LENGTH_LONG).show()
    }
}