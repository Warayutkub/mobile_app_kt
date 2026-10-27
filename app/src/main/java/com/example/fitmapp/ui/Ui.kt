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

/** เปลี่ยนสีพื้นของ View ที่ใช้ shape bg_round_* (เช่นป้าย วท.บ. ตามสีหลักสูตร) */
fun View.tintBackground(@ColorRes color: Int) {
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
}

/** 19000 → "19,000" */
fun Int.withComma(): String = NumberFormat.getIntegerInstance(Locale.US).format(this)

/**
 * แอปวาดเต็มจอ (edge-to-edge): ให้ [topView] เว้นที่ status bar และ [root] เว้นที่ navigation bar
 * lightStatusBar = true → ไอคอน status bar สีเข้ม (หน้าพื้นขาว), false → สีขาว (หน้าพื้นน้ำเงิน)
 */
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
