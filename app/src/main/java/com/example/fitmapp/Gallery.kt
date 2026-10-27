package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
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

        // กดรูปไฮไลต์ หรือรูปในตาราง → เปิดดูรูปเต็มจอ (12 Photo Viewer)
        activityView.setOnClickListener { openViewer() }
        findGrid(findViewById(android.R.id.content))?.let { grid ->
            for (i in 0 until grid.childCount) grid.getChildAt(i).setOnClickListener { openViewer() }
        }
    }

    private fun openViewer() {
        startActivity(Intent(this, FullScreenGallery::class.java))
    }

    /** ตารางรูปใน layout ไม่มี id จึงหาจาก GridLayout ตัวแรกในหน้า */
    private fun findGrid(view: View): GridLayout? {
        if (view is GridLayout) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) findGrid(view.getChildAt(i))?.let { return it }
        return null
    }
}
