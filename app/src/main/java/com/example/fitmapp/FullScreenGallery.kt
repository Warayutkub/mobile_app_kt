package com.example.fitmapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
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
        intent.getStringExtra(EXTRA_SOURCE)?.let { findViewById<TextView>(R.id.tvSource).text = "ที่มาภาพ: $it" }
        val image = intent.getIntExtra(EXTRA_IMAGE, 0)
        if (image != 0) {
            val frame = findViewById<ViewGroup>(R.id.layoutCenterPlaceholder)
            frame.children.forEach { it.visibility = View.GONE }
            frame.addView(ImageView(this).apply {
                setImageResource(image)
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            })
        }
    }

    companion object {
        const val EXTRA_CATEGORY = "category"
        const val EXTRA_TITLE = "title"
        const val EXTRA_DESCRIPTION = "description"
        const val EXTRA_IMAGE = "image"
        const val EXTRA_SOURCE = "source"
    }
}
