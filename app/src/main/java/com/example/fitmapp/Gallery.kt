package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitmapp.ui.BottomNav
import com.example.fitmapp.ui.Tab

class Gallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        keepClearOfSystemBars()
        val activityView = findViewById<CardView>(R.id.activityView)
        BottomNav.setupStatic(this, findViewById(R.id.bottomNavigation), Tab.GALLERY)
    }
}