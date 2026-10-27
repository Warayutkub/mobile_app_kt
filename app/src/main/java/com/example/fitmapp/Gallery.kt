package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
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
        showPhoto(activityView, photos[0].image)
        val highlight = activityView.parent as ViewGroup
        (highlight.getChildAt(highlight.indexOfChild(activityView) + 1) as? TextView)?.text = photos[0].title
        findGrid(findViewById(android.R.id.content))?.let { grid ->
            for (i in 0 until grid.childCount) {
                val photo = photos.getOrNull(i + 1) ?: continue
                val item = grid.getChildAt(i) as ViewGroup
                item.setOnClickListener { openViewer(photo) }
                showPhoto(item.getChildAt(0) as ViewGroup, photo.image)
                (item.getChildAt(1) as TextView).text = photo.title
                (item.getChildAt(2) as TextView).text = "ภาพ: ${photo.source}"
            }
        }
    }

    private val photos = listOf(
        GalleryPhoto("กิจกรรม", "กิจกรรมนักศึกษา FITM", "บรรยากาศคอนเสิร์ตในงานกิจกรรมของนักศึกษาคณะเทคโนโลยีและการจัดการอุตสาหกรรม", R.drawable.photo_gallery_activity, SOURCE),
        GalleryPhoto("บรรยากาศ", "อาคารคณะ FITM", "ป้ายหน้าอาคารคณะเทคโนโลยีและการจัดการอุตสาหกรรม มจพ. วิทยาเขตปราจีนบุรี", R.drawable.photo_gallery_faculty, SOURCE),
        GalleryPhoto("การเรียน", "ห้องแล็บคอมพิวเตอร์", "นักศึกษาฝึกปฏิบัติในห้องปฏิบัติการคอมพิวเตอร์ของภาควิชาเทคโนโลยีสารสนเทศ", R.drawable.photo_gallery_lab, SOURCE)
    )

    private fun showPhoto(card: ViewGroup, image: Int) {
        card.addView(ImageView(this).apply {
            setImageResource(image)
            scaleType = ImageView.ScaleType.CENTER_CROP
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        })
    }

    private fun openViewer(photo: GalleryPhoto) {
        startActivity(Intent(this, FullScreenGallery::class.java)
            .putExtra(FullScreenGallery.EXTRA_CATEGORY, photo.category)
            .putExtra(FullScreenGallery.EXTRA_TITLE, photo.title)
            .putExtra(FullScreenGallery.EXTRA_DESCRIPTION, photo.description)
            .putExtra(FullScreenGallery.EXTRA_IMAGE, photo.image)
            .putExtra(FullScreenGallery.EXTRA_SOURCE, photo.source))
    }

    private fun findGrid(view: View): GridLayout? {
        if (view is GridLayout) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) findGrid(view.getChildAt(i))?.let { return it }
        return null
    }
}

private const val SOURCE = "เว็บคณะ FITM"

data class GalleryPhoto(val category: String, val title: String, val description: String, val image: Int, val source: String)
