package com.example.fitmapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        keepClearOfSystemBars()
        findViewById<android.view.View>(R.id.btnBack).setOnClickListener { finish() }

    }
}