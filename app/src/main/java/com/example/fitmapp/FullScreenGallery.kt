package com.example.fitmapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars

class FullScreenGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_gallery)
        keepClearOfSystemBars()
        findViewById<View>(R.id.btnClose).setOnClickListener { finish() }

        intent.getStringExtra(EXTRA_CATEGORY)?.let { findViewById<TextView>(R.id.tvCategory).text = it }
        intent.getStringExtra(EXTRA_TITLE)?.let { findViewById<TextView>(R.id.tvTitle).text = it }
        intent.getStringExtra(EXTRA_DESCRIPTION)?.let { findViewById<TextView>(R.id.tvDescription).text = it }
    }

    companion object {
        const val EXTRA_CATEGORY = "category"
        const val EXTRA_TITLE = "title"
        const val EXTRA_DESCRIPTION = "description"
    }
}
