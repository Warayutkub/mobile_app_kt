package com.example.fitmapp.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Screens {

    const val EXTRA_PROGRAM_ID = "program_id"

    const val ABOUT = "About"
    const val LEARN = "Learn"
    const val CAREERS = "Careers"
    const val GALLERY = "Gallery"
    const val CONTACT = "Contact"

    const val QUALIFICATION = "N_07_Qualification"
    const val FEES = "N_08_Fees"

    private const val PACKAGE = "com.example.fitmapp"

    fun open(activity: Activity, screen: String, programId: String? = null, flags: Int = 0) {
        val target = try {
            Class.forName("$PACKAGE.$screen")
        } catch (e: ClassNotFoundException) {
            Toast.makeText(activity, "หน้านี้กำลังพัฒนา", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(activity, target).addFlags(flags)
        if (programId != null) intent.putExtra(EXTRA_PROGRAM_ID, programId)
        try {
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity, "ยังไม่ได้เพิ่ม $screen ใน AndroidManifest", Toast.LENGTH_SHORT).show()
        }
    }

    fun openUrl(activity: Activity, url: String) {
        try {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity, "ไม่พบเบราว์เซอร์ในเครื่อง", Toast.LENGTH_SHORT).show()
        }
    }
}
