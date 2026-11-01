package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.example.fitmapp.data.LearnInfo
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.Screens

class Learn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        keepClearOfSystemBars()
        findViewById<android.view.View>(R.id.btnBack).setOnClickListener { finish() }
        val programId = intent.getStringExtra(Screens.EXTRA_PROGRAM_ID)
        val gotoCareers = findViewById<CardView>(R.id.gotoCareersBtn)
        gotoCareers.setOnClickListener {
            startActivity(Intent(this, Careers::class.java).putExtra(Screens.EXTRA_PROGRAM_ID, programId))
        }

        if (programId != null) {
            val program = ProgramRepository.program(programId)
            program.learn?.let { showLearn(it) }
            if (program.careers.isNotEmpty()) showCareerCount(gotoCareers, program.careers.size)
        }
    }

    private fun showCareerCount(view: View, count: Int) {
        if (view is TextView && view.text.contains("สายอาชีพ")) view.text = "ดู $count สายอาชีพหลังเรียนจบ"
        if (view is ViewGroup) view.children.forEach { showCareerCount(it, count) }
    }

    private fun showLearn(learn: LearnInfo) {
        findViewById<TextView>(R.id.tvLearnIntro).text = learn.intro
        findViewById<TextView>(R.id.tvGroupsTitle).text = learn.groupsTitle

        val grid = findViewById<GridLayout>(R.id.gridGroups)
        learn.groups.take(grid.childCount).forEachIndexed { i, group ->
            val card = (grid.getChildAt(i) as ViewGroup).getChildAt(0) as ViewGroup
            val texts = card.children.filterIsInstance<TextView>().toList()
            texts[0].text = group.nameTh
            texts[1].text = group.nameEn
            texts[2].text = group.courses
        }

        val list = findViewById<LinearLayout>(R.id.listOutcomes)
        list.children.forEachIndexed { i, row ->
            val text = learn.outcomes.getOrNull(i)
            row.visibility = if (text == null) View.GONE else View.VISIBLE
            if (text != null) ((row as ViewGroup).getChildAt(1) as TextView).text = text
        }
    }
}