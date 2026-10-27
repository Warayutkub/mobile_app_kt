package com.example.fitmapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars

class FullScreenGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_gallery)
        keepClearOfSystemBars()
        findViewById<View>(R.id.btnClose).setOnClickListener { finish() }
    }
}
