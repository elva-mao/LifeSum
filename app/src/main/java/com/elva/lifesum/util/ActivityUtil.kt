package com.elva.lifesum.util

import android.app.Activity
import android.widget.Toast

/**
 * Can show [Toast] from every [Activity].
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}