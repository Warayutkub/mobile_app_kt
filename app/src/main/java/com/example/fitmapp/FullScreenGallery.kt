package com.example.fitmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars

/**
 * 12 Photo Viewer — layout ของเพื่อน (เดิมอยู่ใน activity_main.xml ย้ายมาไฟล์ของตัวเองตอน merge
 * เพราะ activity_main.xml คือหน้า Splash) ยังไม่ได้แก้หน้าตาใดๆ
 * มีใน AndroidManifest แล้วในชื่อ .FullScreenGallery
 */
class FullScreenGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_gallery)
        keepClearOfSystemBars()
    }
}
