package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.applySystemBars

/** 01 Splash: แสดง 1.5 วินาที (หรือแตะจอ) แล้วไปหน้า Home */
class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val goHome = Runnable { openHome() }
    private var startedAt = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val root = findViewById<View>(R.id.main)
        applySystemBars(root, root, lightStatusBar = true)

        // หมุนจอแล้วไม่เริ่มนับเวลาใหม่
        startedAt = savedInstanceState?.getLong(KEY_STARTED_AT) ?: SystemClock.elapsedRealtime()
        root.setOnClickListener { openHome() }
    }

    override fun onResume() {
        super.onResume()
        val left = SPLASH_MS - (SystemClock.elapsedRealtime() - startedAt)
        handler.postDelayed(goHome, left.coerceAtLeast(0))
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(goHome)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_STARTED_AT, startedAt)
    }

    private fun openHome() {
        handler.removeCallbacks(goHome)
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // กด Back จาก Home แล้วไม่ย้อนกลับมา Splash
    }

    private companion object {
        const val SPLASH_MS = 1500L
        const val KEY_STARTED_AT = "started_at"
    }
}
