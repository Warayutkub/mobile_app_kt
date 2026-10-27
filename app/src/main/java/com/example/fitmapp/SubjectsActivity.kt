package com.example.fitmapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.Course
import com.example.fitmapp.data.Program
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.BottomNav
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.Tab
import com.example.fitmapp.ui.applySystemBars
import com.example.fitmapp.ui.tintBackground

/** 05 Subjects: รายวิชาของหลักสูตร เลือกภาคการศึกษาด้วย Spinner */
class SubjectsActivity : AppCompatActivity() {

    private lateinit var program: Program
    private lateinit var adapter: SubjectAdapter
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subjects)
        applySystemBars(findViewById(R.id.main), findViewById(R.id.appBar), lightStatusBar = true)
        BottomNav.setup(this, Tab.PROGRAMS)

        program = ProgramRepository.program(intent.getStringExtra(Screens.EXTRA_PROGRAM_ID))

        findViewById<TextView>(R.id.appBarTitle).text = "รายวิชา"
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<TextView>(R.id.subjectsProgram).apply {
            text = "${program.degree} ${program.name} · ${program.plan}"
            setTextColor(getColor(R.color.primary_700))
            tintBackground(program.tone.tint)
        }

        setupList()
        setupTermPicker(savedInstanceState?.getInt(KEY_TERM) ?: 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TERM, spinner.selectedItemPosition)
    }

    private fun setupList() {
        val list = findViewById<ListView>(R.id.subjectList)
        adapter = SubjectAdapter(this, mutableListOf())
        list.emptyView = findViewById(R.id.subjectEmpty)

        // แนวตั้ง: บอกแหล่งที่มาท้ายรายการ (แนวนอนมีอยู่ในคอลัมน์ซ้ายแล้ว)
        if (findViewById<View>(R.id.subjectsSource) == null) {
            val source = layoutInflater.inflate(R.layout.item_term_dropdown, list, false) as TextView
            source.setTextAppearance(R.style.Text_Caption)
            source.setTextColor(getColor(R.color.subtle))
            source.text = getString(R.string.subjects_source)
            list.addFooterView(source, null, false)
        }
        list.adapter = adapter
        list.setOnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let { showCourseDetail(it) }
        }
    }

    private fun setupTermPicker(selected: Int) {
        val terms = program.terms
        spinner = findViewById(R.id.termSpinner)
        val termAdapter = ArrayAdapter(this, R.layout.item_term_selected, terms.map { it.title })
        termAdapter.setDropDownViewResource(R.layout.item_term_dropdown)
        spinner.adapter = termAdapter
        findViewById<View>(R.id.termPicker).setOnClickListener { spinner.performClick() }
        findViewById<View>(R.id.termPicker).visibility = if (terms.isEmpty()) View.GONE else View.VISIBLE

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showTerm(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        if (terms.isEmpty()) showTerm(-1) else spinner.setSelection(selected.coerceIn(0, terms.lastIndex))
    }

    private fun showTerm(index: Int) {
        val term = program.terms.getOrNull(index)
        adapter.clear()
        if (term != null) adapter.addAll(term.courses)
        adapter.notifyDataSetChanged()
        findViewById<TextView>(R.id.subjectsCount).text =
            if (term == null) "ยังไม่มีข้อมูล" else "${term.courses.size} วิชา · ${term.totalCredits} หน่วยกิต"
    }

    /** กดวิชาแล้วเปิดรายละเอียด (หน้า 06 Subject Dialog) */
    private fun showCourseDetail(course: Course) {
        val termTitle = program.terms.getOrNull(spinner.selectedItemPosition)?.title ?: ""
        SubjectDialog.show(this, course, termTitle, ProgramRepository.courseName(program.id, course.prerequisite))
    }

    private companion object {
        const val KEY_TERM = "selected_term"
    }
}
