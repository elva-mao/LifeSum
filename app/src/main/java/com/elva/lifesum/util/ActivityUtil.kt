package com.elva.lifesum.util

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Can show [Toast] from every [Activity].
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

fun Activity.showSnackbar(view : View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}