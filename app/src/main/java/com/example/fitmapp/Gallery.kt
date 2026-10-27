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

        activityView.setOnClickListener { openViewer(photos[0]) }
        findGrid(findViewById(android.R.id.content))?.let { grid ->
            for (i in 0 until grid.childCount) {
                val photo = photos.getOrNull(i + 1) ?: continue
                grid.getChildAt(i).setOnClickListener { openViewer(photo) }
            }
        }
    }

    private val photos = listOf(
        GalleryPhoto("ค่าย & อบรม", "IT Camp 2569", "ค่ายพัฒนาทักษะนักศึกษาใหม่ของภาควิชาเทคโนโลยีสารสนเทศ"),
        GalleryPhoto("ค่าย & อบรม", "ค่ายพัฒนาทักษะ IT", "กิจกรรมฝึกทักษะด้านเทคโนโลยีสารสนเทศให้นักศึกษา"),
        GalleryPhoto("แนะแนว", "Open House FITM", "นักศึกษาภาควิชา IT แนะนำหลักสูตรและพาน้อง ๆ ทดลองเขียนโปรแกรมในห้องปฏิบัติการ")
    )

    private fun openViewer(photo: GalleryPhoto) {
        startActivity(Intent(this, FullScreenGallery::class.java)
            .putExtra(FullScreenGallery.EXTRA_CATEGORY, photo.category)
            .putExtra(FullScreenGallery.EXTRA_TITLE, photo.title)
            .putExtra(FullScreenGallery.EXTRA_DESCRIPTION, photo.description))
    }

    private fun findGrid(view: View): GridLayout? {
        if (view is GridLayout) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) findGrid(view.getChildAt(i))?.let { return it }
        return null
    }
}

data class GalleryPhoto(val category: String, val title: String, val description: String)
