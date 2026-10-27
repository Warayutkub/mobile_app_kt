package com.example.fitmapp


import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.ProgramData
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.keepClearOfSystemBars

class N_07_Qualification : AppCompatActivity() {

    private lateinit var radioGroupQualification: RadioGroup
    private lateinit var layoutResultCard: LinearLayout
    private lateinit var tvResultCount: TextView
    private lateinit var tvCourse1: TextView
    private lateinit var tvCourse2: TextView
    private lateinit var tvCourse3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n07_qualification)
        keepClearOfSystemBars()
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<View>(R.id.btnSubmit).setOnClickListener { Screens.openUrl(this, ProgramData.APPLY_URL) }

        radioGroupQualification = findViewById(R.id.radioGroupQualification)
        layoutResultCard = findViewById(R.id.layoutResultCard)
        tvResultCount = findViewById(R.id.tvResultCount)
        tvCourse1 = findViewById(R.id.tvCourse1)
        tvCourse2 = findViewById(R.id.tvCourse2)
        tvCourse3 = findViewById(R.id.tvCourse3)

        radioGroupQualification.setOnCheckedChangeListener { _, checkedId ->
            layoutResultCard.visibility = View.VISIBLE
            updateEligibleCourses(checkedId)
        }
    }

    private fun updateEligibleCourses(selectedRadioId: Int) {
        val programIds = when (selectedRadioId) {
            R.id.radioSciMath, R.id.radioOtherHighSchool, R.id.radioVocational ->
                listOf("bsc_it_4y", "beng_ine_4y")
            R.id.radioHighVocationalBiz -> listOf("bsc_it_transfer", "beng_ine_transfer", "bind_it_2y")
            R.id.radioHighVocationalTech -> listOf("beng_ine_transfer", "bind_it_2y")
            else -> emptyList()
        }
        tvResultCount.text = "สมัครได้ ${programIds.size} หลักสูตร"
        listOf(tvCourse1, tvCourse2, tvCourse3).forEachIndexed { i, tv ->
            val id = programIds.getOrNull(i)
            tv.visibility = if (id == null) View.GONE else View.VISIBLE
            if (id != null) tv.text = courseLabel(id)
        }
    }

    private fun courseLabel(programId: String): String {
        val p = ProgramRepository.program(programId)
        return if (p.name.endsWith(")")) "${p.degree} ${p.name}" else "${p.degree} ${p.name} (${p.plan})"
    }
}
