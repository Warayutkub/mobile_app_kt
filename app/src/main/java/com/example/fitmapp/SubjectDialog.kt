package com.example.fitmapp

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toDrawable
import com.example.fitmapp.data.Course

object SubjectDialog {

    fun show(activity: Activity, course: Course, termTitle: String, prerequisiteName: String?) {
        // 1. ดึง Layout XML ที่สร้างไว้มาใช้งาน (Inflate)
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.subject_dialog, null)
        // 2. สร้าง AlertDialog
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        val dialog = builder.create()
        // 3. ปรับพื้นหลังรอบ Dialog ให้โปร่งใส (เพื่อให้ CardView แสดงมุมโค้งสวยงาม)
        dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

        dialogView.findViewById<TextView>(R.id.tvSubjectCode).text = course.code
        dialogView.findViewById<TextView>(R.id.tvSubjectTitleTh).text = course.nameTh
        dialogView.findViewById<TextView>(R.id.tvSubjectTitleEn).text = course.nameEn
        dialogView.findViewById<TextView>(R.id.tvCredits).text = course.credits.toString()
        val hours = course.hours.split("-").takeIf { it.size == 3 }
        dialogView.findViewById<TextView>(R.id.tvLectureHours).text = hours?.get(0) ?: "-"
        dialogView.findViewById<TextView>(R.id.tvLabHours).text = hours?.get(1) ?: "-"
        dialogView.findViewById<TextView>(R.id.tvSelfHours).text = hours?.get(2) ?: "-"
        val prerequisite = when {
            course.prerequisite.isEmpty() || course.prerequisite == "None" -> "ไม่มี"
            prerequisiteName != null -> "${course.prerequisite} $prerequisiteName"
            else -> course.prerequisite
        }
        dialogView.findViewById<TextView>(R.id.tvPrerequisite).text = "วิชาบังคับก่อน: $prerequisite - เรียน$termTitle"
        dialogView.findViewById<TextView>(R.id.tvDescription).text = when {
            course.descriptionTh.isNotEmpty() -> course.descriptionTh
            course.descriptionEn.isNotEmpty() -> course.descriptionEn
            course.hours.contains("ชั่วโมง") -> "${course.nameTh} ${course.hours}"
            else -> "ดูคำอธิบายรายวิชาได้ในเล่มหลักสูตร"
        }

        // 4. ผูก View ปุ่มปิด
        val btnCloseButton = dialogView.findViewById<Button>(R.id.btnCloseButton)
        // 5. ตั้งค่าการปิด Dialog
        btnCloseButton.setOnClickListener { dialog.dismiss() }
        // 6. สั่งแสดง Dialog
        dialog.show()
    }
}
