package com.example.fitmapp.ui

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fitmapp.HomeActivity
import com.example.fitmapp.ProgramsActivity
import com.example.fitmapp.R

enum class Tab { HOME, PROGRAMS, GALLERY, CONTACT }

/**
 * แถบเมนูล่าง (layout/view_bottom_nav.xml)
 * ใช้ในหน้าไหน: <include layout="@layout/view_bottom_nav" /> แล้วเรียก BottomNav.setup(this, Tab.X)
 */
object BottomNav {

    private class Item(val tab: Tab, val root: Int, val pill: Int, val icon: Int, val label: Int)

    private val items = listOf(
        Item(Tab.HOME, R.id.nav_home, R.id.nav_home_pill, R.id.nav_home_icon, R.id.nav_home_label),
        Item(Tab.PROGRAMS, R.id.nav_course, R.id.nav_course_pill, R.id.nav_course_icon, R.id.nav_course_label),
        Item(Tab.GALLERY, R.id.nav_activity, R.id.nav_activity_pill, R.id.nav_activity_icon, R.id.nav_activity_label),
        Item(Tab.CONTACT, R.id.nav_contact, R.id.nav_contact_pill, R.id.nav_contact_icon, R.id.nav_contact_label)
    )

    fun setup(activity: Activity, current: Tab) {
        val active = ContextCompat.getColor(activity, R.color.primary_700)
        val idle = ContextCompat.getColor(activity, R.color.muted)
        val pillOn = ContextCompat.getColor(activity, R.color.primary_100)

        for (item in items) {
            val selected = item.tab == current
            activity.findViewById<FrameLayout>(item.pill).backgroundTintList =
                ColorStateList.valueOf(if (selected) pillOn else Color.TRANSPARENT)
            activity.findViewById<ImageView>(item.icon).imageTintList =
                ColorStateList.valueOf(if (selected) active else idle)
            activity.findViewById<TextView>(item.label).apply {
                setTextColor(if (selected) active else idle)
                typeface = if (selected) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            }
            activity.findViewById<LinearLayout>(item.root).setOnClickListener {
                if (!selected) go(activity, item.tab)
            }
        }
    }

    /**
     * แถบเมนูล่างที่วาดเองใน layout (หน้า Contact / Gallery ของเพื่อน): LinearLayout ที่มีลูก 4 ช่อง
     * เรียงตาม หน้าแรก / หลักสูตร / กิจกรรม / ติดต่อ — ผูกการกดอย่างเดียว ไม่เปลี่ยนหน้าตา
     */
    fun setupStatic(activity: Activity, container: ViewGroup, current: Tab) {
        val order = listOf(Tab.HOME, Tab.PROGRAMS, Tab.GALLERY, Tab.CONTACT)
        for (i in 0 until minOf(container.childCount, order.size)) {
            val tab = order[i]
            container.getChildAt(i).setOnClickListener { if (tab != current) go(activity, tab) }
        }
    }

    private fun go(activity: Activity, tab: Tab) {
        when (tab) {
            Tab.HOME -> activity.startActivity(
                Intent(activity, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
            Tab.PROGRAMS -> activity.startActivity(
                Intent(activity, ProgramsActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            )
            // แท็บหลักเปิดซ้ำไม่ซ้อนกันในประวัติ (กด Back แล้วไม่ต้องย้อนหลายรอบ)
            Tab.GALLERY -> Screens.open(activity, Screens.GALLERY, flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            Tab.CONTACT -> Screens.open(activity, Screens.CONTACT, flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
    }
}
