package com.example.fitmapp


import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.ProgramData
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.keepClearOfSystemBars

class N_07_Qualification : AppCompatActivity() {

    private lateinit var radioGroupQualification: RadioGroup
    private lateinit var layoutResultCard: LinearLayout
    private lateinit var tvResultCount: TextView
    private lateinit var tvCourse1: TextView
    private lateinit var tvCourse2: TextView

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

        radioGroupQualification.setOnCheckedChangeListener { _, checkedId ->
            layoutResultCard.visibility = View.VISIBLE
            updateEligibleCourses(checkedId)
        }
    }

    private fun updateEligibleCourses(selectedRadioId: Int) {
        when (selectedRadioId) {
            R.id.radioSciMath -> {
                tvResultCount.text = "สมัครได้ 2 หลักสูตร"
                tvCourse1.text = "วท.บ. เทคโนโลยีสารสนเทศ (4 ปี)"
                tvCourse1.visibility = View.VISIBLE
                tvCourse2.text = "วศ.บ. วิศวกรรมสารสนเทศและเครือข่าย (4 ปี)"
                tvCourse2.visibility = View.VISIBLE
            }
            R.id.radioOtherHighSchool -> {
                tvResultCount.text = "สมัครได้ 1 หลักสูตร"
                tvCourse1.text = "วท.บ. เทคโนโลยีสารสนเทศ (4 ปี)"
                tvCourse1.visibility = View.VISIBLE
                tvCourse2.visibility = View.GONE
            }
            R.id.radioVocational -> {
                tvResultCount.text = "สมัครได้ 1 หลักสูตร"
                tvCourse1.text = "วท.บ. เทคโนโลยีสารสนเทศ (4 ปี)"
                tvCourse1.visibility = View.VISIBLE
                tvCourse2.visibility = View.GONE
            }
            R.id.radioHighVocationalBiz, R.id.radioHighVocationalTech -> {
                tvResultCount.text = "สมัครได้ 2 หลักสูตร"
                tvCourse1.text = "วท.บ. เทคโนโลยีสารสนเทศ (เทียบโอน)"
                tvCourse1.visibility = View.VISIBLE
                tvCourse2.text = "วศ.บ. วิศวกรรมสารสนเทศและเครือข่าย (เทียบโอน)"
                tvCourse2.visibility = View.VISIBLE
            }
        }
    }
}