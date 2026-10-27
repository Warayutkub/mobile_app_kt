package com.example.fitmapp.ui

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

fun View.tintBackground(@ColorRes color: Int) {
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
}

fun Int.withComma(): String = NumberFormat.getIntegerInstance(Locale.US).format(this)

fun AppCompatActivity.applySystemBars(root: View, topView: View, lightStatusBar: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = lightStatusBar
    val topStart = topView.paddingTop
    ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(bars.left, 0, bars.right, bars.bottom)
        topView.setPadding(topView.paddingLeft, topStart + bars.top, topView.paddingRight, topView.paddingBottom)
        insets
    }
}

fun AppCompatActivity.keepClearOfSystemBars() {
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
        insets
    }
}
