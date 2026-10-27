package com.example.fitmapp.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/**
 * เปิดหน้าอื่นของแอป
 *
 * หน้าของเพื่อนในกลุ่มเรียกด้วยชื่อคลาส (ค่าคงที่ข้างล่าง) — เมื่อเพื่อนสร้าง Activity ชื่อนี้
 * และลงทะเบียนใน AndroidManifest แล้ว ปุ่มที่ลิงก์ไว้จะเปิดหน้านั้นได้เองโดยไม่ต้องแก้โค้ดตรงนี้
 * ถ้ายังไม่มี จะขึ้น Toast "หน้านี้กำลังพัฒนา"
 */
object Screens {

    /** ส่ง id หลักสูตรข้ามหน้า: intent.getStringExtra(Screens.EXTRA_PROGRAM_ID) */
    const val EXTRA_PROGRAM_ID = "program_id"

    const val ABOUT = "AboutActivity"
    const val QUALIFICATION = "QualificationActivity"
    const val FEES = "FeesActivity"
    const val LEARN = "LearnActivity"
    const val CAREERS = "CareersActivity"
    const val GALLERY = "GalleryActivity"
    const val CONTACT = "ContactActivity"

    private const val PACKAGE = "com.example.fitmapp"

    fun open(activity: Activity, screen: String, programId: String? = null) {
        val target = try {
            Class.forName("$PACKAGE.$screen")
        } catch (e: ClassNotFoundException) {
            Toast.makeText(activity, "หน้านี้กำลังพัฒนา", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(activity, target)
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
