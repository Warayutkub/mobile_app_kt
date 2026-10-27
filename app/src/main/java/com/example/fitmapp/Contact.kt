package com.example.fitmapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.ProgramData
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.keepClearOfSystemBars
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitmapp.ui.BottomNav
import com.example.fitmapp.ui.Tab

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        keepClearOfSystemBars()
        BottomNav.setupStatic(this, findViewById(R.id.bottomNavigation), Tab.CONTACT)

        findViewById<View>(R.id.btnMap).setOnClickListener {
            open(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(MAP_QUERY))))
        }
        findViewById<View>(R.id.btnCall).setOnClickListener { open(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$HOTLINE"))) }
        findViewById<View>(R.id.btnWebIT).setOnClickListener { Screens.openUrl(this, IT_SITE_URL) }
        findViewById<View>(R.id.btnWebFaculty).setOnClickListener { Screens.openUrl(this, FACULTY_URL) }
        findViewById<View>(R.id.btnApply).setOnClickListener { Screens.openUrl(this, ProgramData.APPLY_URL) }
    }

    private fun open(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "ไม่พบแอปที่เปิดได้ในเครื่อง", Toast.LENGTH_SHORT).show()
        }
    }

    private companion object {
        const val HOTLINE = "0659349469"
        const val MAP_QUERY = "คณะเทคโนโลยีและการจัดการอุตสาหกรรม มจพ. ปราจีนบุรี"
        const val IT_SITE_URL = "https://sites.google.com/itm.kmutnb.ac.th/it-fitm"
        const val FACULTY_URL = "https://www.fitm.kmutnb.ac.th/"
    }
}
